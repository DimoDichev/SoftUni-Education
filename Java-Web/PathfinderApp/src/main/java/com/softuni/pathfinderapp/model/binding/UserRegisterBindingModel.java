package com.softuni.pathfinderapp.model.binding;

import jakarta.validation.constraints.*;

public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters!")
    private String username;
    @NotNull
    @Size(min = 4, max = 40, message = "Full name must be between 4 and 40 characters!")
    private String fullName;
    @NotNull
    @Email(message = "Enter valid email!")
    private String email;
    @NotNull
    @Min(value = 14, message = "Age must be between 14 and 90!")
    @Max(value = 90, message = "Age must be between 14 and 90!")
    private Integer age;
    @NotNull
    @Size(min = 5, max = 250, message = "Password length must be between 5 and 250 characters!")
    private String password;
    @NotNull
    @Size(min = 5, max = 250)
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
