package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserLoginDto;
import com.dictionaryapp.model.dto.UserRegistrationDto;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.LoggedUser;
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
        UserEntity user = userRepository.findByUsername(userLoginDto.getUsername()).orElse(null);

        boolean loginSuccessful = false;

        if (user != null) {
            loginSuccessful = passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
        }

        if (loginSuccessful) {
            loggedUser.setUsername(user.getUsername());
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
