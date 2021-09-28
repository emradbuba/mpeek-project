package com.gitlab.emradbuba.training.mpeek.provider.github;

import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import com.gitlab.emradbuba.training.mpeek.properties.AppProperties;
import com.gitlab.emradbuba.training.mpeek.provider.exception.UserCalculationsException;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GithubUserCalculator {
    private final AppProperties appProperties;

    public double calculate(GithubUserDTO githubUserDTO) throws UserCalculationsException {
        throwIfNotCalculable(githubUserDTO);
        double numberOfFollowersDouble = githubUserDTO.getNumberOfFollowers();
        double numberOfPublicReposDouble = githubUserDTO.getNumberOfPublicRepos();
        double result = 6.0 / numberOfFollowersDouble * (2.0 + numberOfPublicReposDouble);
        return roundResult(result);
    }

    private double roundResult(double theResult) {
        int precision = appProperties.getCalculationsRoundingPrecision();
        return Precision.round(theResult, precision);
    }

    private void throwIfNotCalculable(GithubUserDTO githubUserDTO) throws UserCalculationsException {
        String githubUserName = githubUserDTO.getName();
        if (githubUserDTO.getNumberOfFollowers() == 0) {
            String message = String.format("Could not perform calculations. User '%s' has no followers", githubUserName);
            throw new UserCalculationsException(message);
        }
    }
}