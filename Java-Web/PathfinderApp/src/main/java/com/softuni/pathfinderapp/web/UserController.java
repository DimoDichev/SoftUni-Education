package com.softuni.pathfinderapp.web;

import com.softuni.pathfinderapp.model.binding.UserRegisterBindingModel;
import com.softuni.pathfinderapp.model.view.UserProfileViewModel;
import com.softuni.pathfinderapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("passwordNotEquals", true);
            return "redirect:register";
        }

        boolean usernameExist = userService.findIfUserExist(userRegisterBindingModel);
        boolean emailExist = userService.findIfEmailExist(userRegisterBindingModel);

        if (usernameExist) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("usernameExist", true);
            return "redirect:register";
        }

        if (emailExist) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel).
                    addFlashAttribute("emailExist", true);
            return "redirect:register";
        }

        userService.registerUser(userRegisterBindingModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailure(@ModelAttribute("username") String username , Model model) {
            model.addAttribute("username", username)
                    .addAttribute("bad_credentials", "true");
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("userProfileViewModel", userService.findByUsername(currentUser.getUsername()));
        return "profile";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

}
