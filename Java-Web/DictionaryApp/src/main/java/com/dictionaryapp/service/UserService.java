package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDto;
import com.dictionaryapp.model.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    boolean findIfUsernameExist(UserRegistrationDto userRegistrationDto);

    boolean findIfEmailExist(UserRegistrationDto userRegistrationDto);

    void registerUser(UserRegistrationDto userRegistrationDto);

    boolean loginUser(UserLoginDto userLoginDto);

    void logout(HttpSession httpSession);
}
