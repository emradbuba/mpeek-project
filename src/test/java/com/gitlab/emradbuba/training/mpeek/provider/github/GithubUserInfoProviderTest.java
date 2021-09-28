package com.gitlab.emradbuba.training.mpeek.provider.github;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import com.gitlab.emradbuba.training.mpeek.github.client.GithubRestClientException;
import com.gitlab.emradbuba.training.mpeek.github.client.RestGithubClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GithubUserInfoProviderTest {
    private static final GithubUserDTO GITHUB_USER_DTO = new GithubUserDTO();
    private static final UserDTO USER_DTO = UserDTO.builder().build();
    private GithubUserInfoProvider provider;
    private RestGithubClient githubClient;
    private GithubUserToUserDtoConverter converter;

    @BeforeEach
    void setUp() {
        githubClient = mock(RestGithubClient.class);
        converter = mock(GithubUserToUserDtoConverter.class);
        provider = new GithubUserInfoProvider(githubClient, converter);
    }

    @Test
    void shouldReturnCorrectUserDto() throws GithubRestClientException {
        final String TEST_LOGIN = "testLogin";
        when(githubClient.getGithubUser(TEST_LOGIN)).thenReturn(GITHUB_USER_DTO);
        when(converter.convert(GITHUB_USER_DTO)).thenReturn(USER_DTO);

        Optional<UserDTO> result = provider.getUserByLogin(TEST_LOGIN);

        assertTrue(result.isPresent());
        assertEquals(USER_DTO, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenRestClientThrowsException() throws GithubRestClientException {
        final String TEST_LOGIN = "testLogin";
        when(githubClient.getGithubUser(TEST_LOGIN))
                .thenThrow(new GithubRestClientException("Test exception. Ignore it!"));

        Optional<UserDTO> result = provider.getUserByLogin(TEST_LOGIN);

        assertTrue(result.isEmpty());
    }
}