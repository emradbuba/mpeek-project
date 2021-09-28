package com.gitlab.emradbuba.training.mpeek.provider.github;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import com.gitlab.emradbuba.training.mpeek.github.client.GithubRestClientException;
import com.gitlab.emradbuba.training.mpeek.github.client.RestGithubClient;
import com.gitlab.emradbuba.training.mpeek.provider.UserInfoProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class GithubUserInfoProvider implements UserInfoProvider {
    private final RestGithubClient githubClient;
    private final GithubUserToUserDtoConverter githubUserToUserDtoConverter;

    @Override
    public Optional<UserDTO> getUserByLogin(String login) {
        try {
            GithubUserDTO gitHubUser = githubClient.getGithubUser(login);
            return Optional.ofNullable(gitHubUser)
                    .map(githubUserToUserDtoConverter::convert)
                    .or(Optional::empty);
        } catch (GithubRestClientException | RestClientException e) {
            log.error("Error when getting information from github", e);
            return Optional.empty();
        }
    }
}