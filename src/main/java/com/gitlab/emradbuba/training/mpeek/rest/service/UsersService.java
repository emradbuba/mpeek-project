package com.gitlab.emradbuba.training.mpeek.rest.service;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.provider.UserInfoProvider;
import com.gitlab.emradbuba.training.mpeek.provider.exception.InfoProviderException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService {
    private final UserInfoProvider userInfoProvider;

    public Optional<UserDTO> getUserByLogin(String login) {
        try {
            return userInfoProvider.getUserByLogin(login);
        } catch (InfoProviderException e) {
            String errorMessage = String.format("Failed to retrieve info about user '%s' from user info provider", login);
            log.error(errorMessage, e);
            return Optional.empty();
        }
    }
}
