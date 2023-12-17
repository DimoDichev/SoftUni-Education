package com.example.resellerapp.service;

import com.example.resellerapp.model.dto.UserLoginDto;
import com.example.resellerapp.model.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpSession;

public interface UserService {

    boolean findIfUsernameExist(UserRegistrationDto userRegistrationDto);

    boolean findIfEmailExist(UserRegistrationDto userRegistrationDto);

    void registerUser(UserRegistrationDto userRegistrationDto);

    boolean loginUser(UserLoginDto userLoginDto);

    void logout(HttpSession httpSession);
}
