package com.gitlab.emradbuba.training.mpeek.github;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.github.client.GithubClient;
import com.gitlab.emradbuba.training.mpeek.provider.UserInfoProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GithubUserInfoProvider implements UserInfoProvider  {
    private final GithubClient githubClient;

    @Override
    public Optional<UserDTO> getUserByLogin(String login) {
        return Optional.empty();
    }
}
