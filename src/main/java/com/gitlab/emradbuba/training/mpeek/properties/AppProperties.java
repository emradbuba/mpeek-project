package com.gitlab.emradbuba.training.mpeek.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppProperties {
    @Value("${github.api.url.users}")
    private String githubApiUsersUrl;
    @Value("${calculations.rounding.precision:5}")
    private Integer calculationsRoundingPrecision;
}