package com.gitlab.emradbuba.training.mpeek.provider.github;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.github.GithubUserDTO;
import com.gitlab.emradbuba.training.mpeek.provider.exception.UserCalculationsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GithubUserToUserDtoConverterTest {
    private static final String ID = "test_id";
    private static final String LOGIN = "test_login";
    private static final String NAME = "test_name";
    private static final String TYPE = "test_type";
    private static final String CREATED_AT = "test_created_at";
    private static final String AVATAR_URL = "test_avatar";
    private GithubUserToUserDtoConverter converter;
    private GithubUserCalculator githubUserCalculator;
    private GithubUserDTO githubUserDTO;

    @BeforeEach
    void setUp() {
        githubUserDTO = createTestGithubUserDto();
        githubUserCalculator = mock(GithubUserCalculator.class);
        converter = new GithubUserToUserDtoConverter(githubUserCalculator);
    }

    @Test
    void shouldConvertGithubUserIdWhenGiven() {
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getId());
        assertEquals(ID, userDTO.getId());
    }

    @Test
    void shouldConvertGithubUserIdAsEmptyStringWhenEmpty() {
        githubUserDTO.setId("");
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getId());
        assertTrue(userDTO.getId().isEmpty());
    }

    @Test
    void shouldConvertGithubUserIdAsEmptyStringWhenNull() {
        githubUserDTO.setId(null);
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getId());
        assertTrue(userDTO.getId().isEmpty());
    }

    @Test
    void shouldConvertGithubUserNameWhenGiven() {
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getName());
        assertEquals(NAME, userDTO.getName());
    }

    @Test
    void shouldConvertGithubUserNameAsEmptyStringWhenEmpty() {
        githubUserDTO.setName("");
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getName());
        assertTrue(userDTO.getName().isEmpty());
    }

    @Test
    void shouldConvertGithubUserNameAsEmptyStringWhenNull() {
        githubUserDTO.setName(null);
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getName());
        assertTrue(userDTO.getName().isEmpty());
    }

    @Test
    void shouldConvertGithubUserLoginWhenGiven() {
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getLogin());
        assertEquals(LOGIN, userDTO.getLogin());
    }

    @Test
    void shouldConvertGithubUserLoginAsEmptyStringWhenEmpty() {
        githubUserDTO.setLogin("");
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getLogin());
        assertTrue(userDTO.getLogin().isEmpty());
    }

    @Test
    void shouldConvertGithubUserLoginAsEmptyStringWhenNull() {
        githubUserDTO.setLogin(null);
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getLogin());
        assertTrue(userDTO.getLogin().isEmpty());
    }

    @Test
    void shouldConvertGithubUserAvatarWhenGiven() {
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getAvatarUrl());
        assertEquals(AVATAR_URL, userDTO.getAvatarUrl());
    }

    @Test
    void shouldConvertGithubUserAvatarAsEmptyStringWhenEmpty() {
        githubUserDTO.setAvatarUrl("");
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getAvatarUrl());
        assertTrue(userDTO.getAvatarUrl().isEmpty());
    }

    @Test
    void shouldConvertGithubUserAvatarAsEmptyStringWhenNull() {
        githubUserDTO.setAvatarUrl(null);
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getAvatarUrl());
        assertTrue(userDTO.getAvatarUrl().isEmpty());
    }

    @Test
    void shouldConvertGithubUserTypeWhenGiven() {
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getType());
        assertEquals(TYPE, userDTO.getType());
    }

    @Test
    void shouldConvertGithubUserTypeAsEmptyStringWhenEmpty() {
        githubUserDTO.setType("");
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getType());
        assertTrue(userDTO.getType().isEmpty());
    }

    @Test
    void shouldConvertGithubUserTypeAsEmptyStringWhenNull() {
        githubUserDTO.setType(null);
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getType());
        assertTrue(userDTO.getType().isEmpty());
    }

    @Test
    void shouldConvertGithubUserCreatedAtWhenGiven() {
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getCreatedAt());
        assertEquals(CREATED_AT, userDTO.getCreatedAt());
    }

    @Test
    void shouldConvertGithubUserCreatedAtAsEmptyStringWhenEmpty() {
        githubUserDTO.setCreatedAt("");
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getCreatedAt());
        assertTrue(userDTO.getCreatedAt().isEmpty());
    }

    @Test
    void shouldConvertGithubUserCreatedAtAsEmptyStringWhenNull() {
        githubUserDTO.setCreatedAt(null);
        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getCreatedAt());
        assertTrue(userDTO.getCreatedAt().isEmpty());
    }

    @Test
    void shouldSetCalculationsResultIfPossible() throws UserCalculationsException {
        final double CALCULATION = 150.0;
        when(githubUserCalculator.calculate(githubUserDTO)).thenReturn(CALCULATION);

        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getCalculations());
        assertEquals(String.valueOf(CALCULATION), userDTO.getCalculations());
    }

    @Test
    void shouldSetCalculationsErrorInfoWhenNotCalculable() throws UserCalculationsException {
        when(githubUserCalculator.calculate(githubUserDTO))
                .thenThrow(new UserCalculationsException("Test exception. Ignore it!"));

        UserDTO userDTO = converter.convert(githubUserDTO);

        assertNotNull(userDTO);
        assertNotNull(userDTO.getCalculations());
        assertFalse(userDTO.getCalculations().isEmpty());
        assertFalse(isDouble(userDTO.getCalculations()));
    }

    private GithubUserDTO createTestGithubUserDto() {
        GithubUserDTO githubUserDTO = new GithubUserDTO();
        githubUserDTO.setId(ID);
        githubUserDTO.setLogin(LOGIN);
        githubUserDTO.setName(NAME);
        githubUserDTO.setType(TYPE);
        githubUserDTO.setCreatedAt(CREATED_AT);
        githubUserDTO.setAvatarUrl(AVATAR_URL);
        githubUserDTO.setNumberOfFollowers(1);
        githubUserDTO.setNumberOfPublicRepos(1);
        return githubUserDTO;
    }

    private boolean isDouble(String doubleAsString) {
        try {
            Double.valueOf(doubleAsString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}