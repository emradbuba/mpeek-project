package com.gitlab.emradbuba.training.mpeek.github.client;

import com.gitlab.emradbuba.training.mpeek.properties.AppProperties;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class RestGithubClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final AppProperties appProperties;

    public GithubUserDTO getGithubUser(String login) throws GithubRestClientException {
        String requestUrl = createUrlForLogin(login);
        ResponseEntity<GithubUserDTO> githubUserDTO = restTemplate.exchange(
                requestUrl, HttpMethod.GET, null, GithubUserDTO.class
        );
        return githubUserDTO.getBody();
    }

    private String createUrlForLogin(String login) throws GithubRestClientException {
        String gitHubApiUrlUsers = appProperties.getGithubApiUsersUrl();
        return Optional.ofNullable(gitHubApiUrlUsers)
                .map(userUrl -> userUrl.concat(login))
                .orElseThrow(() -> new GithubRestClientException("Cannot create github request url. Check properties."));
    }
}
