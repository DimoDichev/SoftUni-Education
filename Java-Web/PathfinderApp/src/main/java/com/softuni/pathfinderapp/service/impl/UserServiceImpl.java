package com.softuni.pathfinderapp.service.impl;

import com.softuni.pathfinderapp.model.binding.UserRegisterBindingModel;
import com.softuni.pathfinderapp.model.entity.RoleEntity;
import com.softuni.pathfinderapp.model.entity.UserEntity;
import com.softuni.pathfinderapp.model.enums.LevelEnum;
import com.softuni.pathfinderapp.model.enums.RoleTypeEnum;
import com.softuni.pathfinderapp.model.view.UserProfileViewModel;
import com.softuni.pathfinderapp.repository.RoleRepository;
import com.softuni.pathfinderapp.repository.UserRepository;
import com.softuni.pathfinderapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        userRepository.save(modelMapper.map(userRegisterBindingModel, UserEntity.class));
    }

    @Override
    public boolean findIfUserExist(UserRegisterBindingModel userRegisterBindingModel) {
        return userRepository.findByUsername(userRegisterBindingModel.getUsername()).orElse(null) != null;
    }

    @Override
    public boolean findIfEmailExist(UserRegisterBindingModel userRegisterBindingModel) {
        return userRepository.findByEmail(userRegisterBindingModel.getEmail()).orElse(null) != null;
    }

    @Override
    public UserProfileViewModel findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return user != null ? modelMapper.map(user, UserProfileViewModel.class) : null;
    }
}
