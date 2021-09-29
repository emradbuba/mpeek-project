package com.gitlab.emradbuba.training.mpeek.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApiCallRegistryTest {
    private static final String EXISTING_GITHUB_LOGIN = "emradbuba";
    private static final Long NUMBER_OF_TEST_REQUESTS = 10000L;
    private static final int NUMBER_OF_THREADS = 50;
    private ApiCallRegistry apiCallRegistry;

    @Autowired
    private ApiCallRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        apiCallRegistry = new ApiCallRegistry(repository);
    }

    @Test
    void shouldRegisterAllParallelApiRequestsForExistingLogin() throws InterruptedException {
        runParallelRequests(NUMBER_OF_TEST_REQUESTS);
        Thread.sleep(10000L);

        Optional<ApiCallDTO> apiCallDto = apiCallRegistry.getApiCallDtoByLogin(EXISTING_GITHUB_LOGIN);

        assertTrue(apiCallDto.isPresent());
        assertEquals(NUMBER_OF_TEST_REQUESTS, apiCallDto.get().getRequestCount());
    }

    private void runParallelRequests(Long numberOfRequests) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < numberOfRequests; i++) {
            executorService.submit(() -> apiCallRegistry.registerApiCall(EXISTING_GITHUB_LOGIN));
        }
    }
}