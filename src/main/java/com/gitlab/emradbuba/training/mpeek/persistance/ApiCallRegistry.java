package com.gitlab.emradbuba.training.mpeek.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ApiCallRegistry {
    private final ApiCallRepository repository;

    public synchronized void registerApiCall(String login) {
        if (!StringUtils.hasText(login)) {
            return;
        }
        ApiCallEntity entity = repository
                .findById(login)
                .orElse(createNewEntity(login));
        incrementCounter(entity);
        repository.save(entity);
    }

    public Optional<ApiCallDTO> getApiCallDtoByLogin(String login) {
        return repository.findById(login)
                .map(ApiCallEntity::asDto)
                .or(Optional::empty);
    }

    private ApiCallEntity createNewEntity(String login) {
        return new ApiCallEntity(login, 0L);
    }

    private void incrementCounter(ApiCallEntity entity) {
        Long requestCount = entity.getRequestCount();
        entity.setRequestCount(requestCount + 1);
    }
}
