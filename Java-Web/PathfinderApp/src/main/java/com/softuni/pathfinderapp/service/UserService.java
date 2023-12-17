package com.softuni.pathfinderapp.service;

import com.softuni.pathfinderapp.model.binding.UserRegisterBindingModel;
import com.softuni.pathfinderapp.model.view.UserProfileViewModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean findIfUserExist(UserRegisterBindingModel userRegisterBindingModel);

    boolean findIfEmailExist(UserRegisterBindingModel userRegisterBindingModel);

    UserProfileViewModel findByUsername(String username);
}
