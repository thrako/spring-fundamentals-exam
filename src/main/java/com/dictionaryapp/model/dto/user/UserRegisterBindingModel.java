package com.dictionaryapp.model.dto.user;

import com.dictionaryapp.validation.anotations.PasswordMatch;
import com.dictionaryapp.validation.anotations.UniqueEmail;
import com.dictionaryapp.validation.anotations.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatch
public class UserRegisterBindingModel {

    @NotBlank(message = "Username must not be empty!")
    @Size(min = 3, max = 20, message = "Username length must be between {min} and {max} characters!")
    @UniqueUsername
    private String username;

    @NotBlank(message = "Password must not be empty!")
    @Size(min = 3, max = 20, message = "Password length must be between {min} and {max} characters!")
    private String password;

    @Email(message = "Must be a well-formed email address")
    @NotBlank(message = "Email must not be empty!")
    @UniqueEmail
    private String email;

    private String confirmPassword;


    public UserRegisterBindingModel() {

    }

    public String getUsername() {

        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {

        this.username = username;
        return this;
    }

    public String getPassword() {

        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {

        this.password = password;
        return this;
    }

    public String getEmail() {

        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {

        this.email = email;
        return this;
    }

    public String getConfirmPassword() {

        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;
        return this;
    }
}
