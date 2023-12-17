package com.example.resellerapp.service.impl;

import com.example.resellerapp.model.dto.UserLoginDto;
import com.example.resellerapp.model.dto.UserRegistrationDto;
import com.example.resellerapp.model.entity.UserEntity;
import com.example.resellerapp.repository.UserRepository;
import com.example.resellerapp.service.UserService;
import com.example.resellerapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean findIfUsernameExist(UserRegistrationDto userRegistrationDto) {
        UserEntity user = userRepository.findByUsername(userRegistrationDto.getUsername()).orElse(null);
        return user != null;
    }

    @Override
    public boolean findIfEmailExist(UserRegistrationDto userRegistrationDto) {
        UserEntity user = userRepository.findByEmail(userRegistrationDto.getEmail()).orElse(null);
        return user != null;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        userRepository.save(mapUser(userRegistrationDto));
    }

    @Override
    public boolean loginUser(UserLoginDto userLoginDto) {
        UserEntity currentUser = userRepository.findByUsername(userLoginDto.getUsername()).orElse(null);

        boolean loginSuccessful = false;

        if (currentUser != null) {
            loginSuccessful = passwordEncoder.matches(userLoginDto.getPassword(), currentUser.getPassword());
        }

        if (loginSuccessful) {
            loggedUser.setUsername(currentUser.getUsername());
            loggedUser.setLogged(true);
        }

        return loginSuccessful;
    }

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    private UserEntity mapUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        return user;
    }
}
