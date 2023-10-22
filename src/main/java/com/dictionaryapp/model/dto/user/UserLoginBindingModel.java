package com.dictionaryapp.model.dto.user;

import javax.validation.constraints.Size;

public class UserLoginBindingModel {
    @Size(min = 3, max = 20, message = "Username length must be between {min} and {max} characters!")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between {min} and {max} characters!")
    private String password;


    public UserLoginBindingModel() {

    }

    public String getUsername() {

        return username;
    }

    public UserLoginBindingModel setUsername(String username) {

        this.username = username;
        return this;
    }

    public String getPassword() {

        return password;
    }

    public UserLoginBindingModel setPassword(String password) {

        this.password = password;
        return this;
    }
}