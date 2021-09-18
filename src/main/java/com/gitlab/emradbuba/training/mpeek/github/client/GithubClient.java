package com.gitlab.emradbuba.training.mpeek.github.client;

import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;

public interface GithubClient {
    GithubUserDTO getGithubUser(String login);
}
