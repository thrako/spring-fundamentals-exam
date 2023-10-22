package com.dictionaryapp.service;

import com.dictionaryapp.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    public boolean isUniqueEmail(String email) {

        return this.userRepository.findByEmail(email).isEmpty();
    }

    public boolean isUniqueUsername(String username) {

        return this.userRepository.findByUsername(username).isEmpty();
    }

}
