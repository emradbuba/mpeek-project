package com.gitlab.emradbuba.training.mpeek.github.client;

import com.gitlab.emradbuba.training.mpeek.GithubApiProperties;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestGithubClient implements GithubClient {
    private final GithubApiProperties githubApiProperties;

    @Override
    public GithubUserDTO getGithubUser(String login) {
        return null;
    }
}
