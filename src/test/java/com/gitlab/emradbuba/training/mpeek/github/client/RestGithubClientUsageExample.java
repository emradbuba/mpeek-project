package com.gitlab.emradbuba.training.mpeek.github.client;

import com.gitlab.emradbuba.training.mpeek.properties.AppProperties;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class RestGithubClientUsageExample {

    @Autowired
    private AppProperties appProperties;

    @Test
    void demo() throws GithubRestClientException {
        RestGithubClient client = new RestGithubClient(appProperties);
        GithubUserDTO radek = client.getGithubUser("emradbuba");
        log.info("" + radek);
    }
}