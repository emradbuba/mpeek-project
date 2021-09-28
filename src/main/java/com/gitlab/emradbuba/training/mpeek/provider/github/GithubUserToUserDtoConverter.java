package com.gitlab.emradbuba.training.mpeek.provider.github;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import com.gitlab.emradbuba.training.mpeek.provider.exception.UserCalculationsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class GithubUserToUserDtoConverter {
    private static final String CANNOT_CALCULATE_VALUE = "(cannot calculate)";
    private final GithubUserCalculator githubUserCalculator;

    public UserDTO convert(GithubUserDTO gitHubUser) {
        return UserDTO.builder()
                .id(sameOrEmpty(gitHubUser.getId()))
                .login(sameOrEmpty(gitHubUser.getLogin()))
                .name(sameOrEmpty(gitHubUser.getName()))
                .type(sameOrEmpty(gitHubUser.getType()))
                .avatarUrl(sameOrEmpty(gitHubUser.getAvatarUrl()))
                .createdAt(sameOrEmpty(gitHubUser.getCreatedAt()))
                .calculations(calculateForUser(gitHubUser))
                .build();
    }

    private String calculateForUser(GithubUserDTO githubUser) {
        try {
            double calculationResult = githubUserCalculator.calculate(githubUser);
            return String.valueOf(calculationResult);
        } catch (UserCalculationsException e) {
            String logMessage = String.format(
                    "Problem while performing calculations for github user %s", githubUser.getLogin()
            );
            log.warn(logMessage, e);
            return CANNOT_CALCULATE_VALUE;
        }
    }

    private String sameOrEmpty(String value) {
        return Optional.ofNullable(value).orElse("");
    }
}