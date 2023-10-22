package com.dictionaryapp.service;

import com.dictionaryapp.exception.LoginException;
import com.dictionaryapp.exception.UserNotFoundException;
import com.dictionaryapp.model.dto.user.UserLoginBindingModel;
import com.dictionaryapp.model.dto.user.UserRegisterBindingModel;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegisterBindingModel userRegisterBindingModel) {

        UserEntity userEntity = mapToUserEntity(userRegisterBindingModel);

        userRepository.save(userEntity);
    }

    private UserEntity mapToUserEntity(UserRegisterBindingModel userRegisterBindingModel) {

        return new UserEntity()
                .setUsername(userRegisterBindingModel.getUsername())
                .setEmail(userRegisterBindingModel.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
    }

    public void verifyCredentials(UserLoginBindingModel userLoginBindingModel) {

        String username = userLoginBindingModel.getUsername();
        UserEntity userEntity = getUserEntity(username);

        if (!passwordEncoder.matches(userLoginBindingModel.getPassword(), userEntity.getPassword())) {
            throw new LoginException("Attempt to log with wrong password by user: " + username);
        }
    }

    private UserEntity getUserEntity(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Username: " + username + "not found!"));
    }
}
