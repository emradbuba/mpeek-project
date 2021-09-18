package com.gitlab.emradbuba.training.mpeek.provider;

import com.gitlab.emradbuba.training.mpeek.dto.UserDTO;
import com.gitlab.emradbuba.training.mpeek.provider.exception.InfoProviderException;

import java.util.Optional;

public interface UserInfoProvider {
    Optional<UserDTO> getUserByLogin(String login) throws InfoProviderException;
}
