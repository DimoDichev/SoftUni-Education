package com.softuni.springdatalab.services;

import com.softuni.springdatalab.models.User;
import com.softuni.springdatalab.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> findUser = userRepository.findByUsername(user.getUsername());

        if (findUser.isPresent()) {
            throw new IllegalArgumentException("User already exist");
        }

        userRepository.save(user);
    }
}
