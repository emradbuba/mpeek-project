package com.gitlab.emradbuba.training.mpeek.provider.github;

import com.gitlab.emradbuba.training.mpeek.properties.AppProperties;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import com.gitlab.emradbuba.training.mpeek.provider.exception.UserCalculationsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GithubUserCalculatorTest {
    private static final int DEFAULT_PRECISION = 3;
    private GithubUserCalculator calculator;
    private AppProperties appProperties;
    private GithubUserDTO githubUser;

    @BeforeEach
    void setUp() {
        appProperties = mock(AppProperties.class);
        githubUser = mock(GithubUserDTO.class);
        calculator = new GithubUserCalculator(appProperties);
    }

    @Test
    void shouldReturnCorrectResultForOneFollowerAndOneRepo() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(DEFAULT_PRECISION);
        when(githubUser.getNumberOfFollowers()).thenReturn(1);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(1);

        double calculation = calculator.calculate(githubUser);

        assertEquals(18.0, calculation);
    }

    @Test
    void shouldReturnCorrectResultForOneFollowerAndZeroRepos() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(DEFAULT_PRECISION);
        when(githubUser.getNumberOfFollowers()).thenReturn(1);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(0);

        double calculation = calculator.calculate(githubUser);

        assertEquals(12.0, calculation);
    }

    @Test
    void shouldReturnCorrectResultForFiveFollowersAndZeroRepos() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(DEFAULT_PRECISION);
        when(githubUser.getNumberOfFollowers()).thenReturn(5);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(0);

        double calculation = calculator.calculate(githubUser);

        assertEquals(2.4, calculation);
    }

    @Test
    void shouldReturnCorrectResultFor49FollowersAnd11ReposWithPrecisionOfFive() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(5);
        when(githubUser.getNumberOfFollowers()).thenReturn(49);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(11);

        double calculation = calculator.calculate(githubUser);

        assertEquals(1.59184, calculation);
    }

    @Test
    void shouldReturnCorrectResultFor49FollowersAnd11ReposWithPrecisionOfEight() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(8);
        when(githubUser.getNumberOfFollowers()).thenReturn(49);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(11);

        double calculation = calculator.calculate(githubUser);

        assertEquals(1.59183673, calculation);
    }

    @Test
    void shouldReturnCorrectResultFor49FollowersAnd11ReposWithPrecisionOfOne() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(1);
        when(githubUser.getNumberOfFollowers()).thenReturn(49);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(11);

        double calculation = calculator.calculate(githubUser);

        assertEquals(1.6, calculation);
    }

    @Test
    void shouldReturnCorrectResultForBigNumbersAndPrecisionOfEight() throws UserCalculationsException {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(8);
        when(githubUser.getNumberOfFollowers()).thenReturn(854122);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(1234);

        double calculation = calculator.calculate(githubUser);

        assertEquals(0.0086826, calculation);
    }

    @Test
    void shouldThrowExceptionWhenZeroFollowers() {
        when(appProperties.getCalculationsRoundingPrecision()).thenReturn(8);
        when(githubUser.getNumberOfFollowers()).thenReturn(0);
        when(githubUser.getNumberOfPublicRepos()).thenReturn(5);

        assertThrows(UserCalculationsException.class, () -> calculator.calculate(githubUser));
    }
}