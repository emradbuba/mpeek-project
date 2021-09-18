package com.gitlab.emradbuba.training.mpeek.rest.controller;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.rest.exceptions.UserNotFoundException;
import com.gitlab.emradbuba.training.mpeek.rest.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UsersRestApi {
    private final UsersService usersService;

    @GetMapping("/{login}")
    public UserDTO getByLogin(@PathVariable("login") String login) {
        log.info("Incoming API request: Get user by login: '{}'", login);
        return usersService.getUserByLogin(login)
                .orElseThrow(getUserNotFoundExceptionSupplier(login));
    }

    private Supplier<UserNotFoundException> getUserNotFoundExceptionSupplier(String login) {
        String errorMessage = String.format("Could not get user with login '%s'", login);
        return () -> new UserNotFoundException(errorMessage);
    }
}
