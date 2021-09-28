package com.gitlab.emradbuba.training.mpeek.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GithubUserDTO {
    private String id;
    private String login;
    private String type;
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("followers")
    private Integer numberOfFollowers;
    @JsonProperty("public_repos")
    private Integer numberOfPublicRepos;
}
