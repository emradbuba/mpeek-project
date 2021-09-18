package com.gitlab.emradbuba.training.mpeek;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class GithubApiProperties {
    private final String githubApiUrl;

    public GithubApiProperties(@Value("github.api.url") String githubApiUrl) {
        this.githubApiUrl = githubApiUrl;
    }
}
