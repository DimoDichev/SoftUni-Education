package com.example.resellerapp.controller;

import com.example.resellerapp.model.dto.UserLoginDto;
import com.example.resellerapp.model.dto.UserRegistrationDto;
import com.example.resellerapp.service.UserService;
import com.example.resellerapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("register")
    public String register(Model model) {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("usernameExist")) {
            model.addAttribute("usernameExist", false);
        }

        if (!model.containsAttribute("emailExist")) {
            model.addAttribute("emailExist", false);
        }

        if (!model.containsAttribute("confirmPasswordDontMatch")) {
            model.addAttribute("confirmPasswordDontMatch", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:register";
        }

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("confirmPasswordDontMatch", true)
                    .addFlashAttribute("userRegistrationDto", userRegistrationDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:register";
        }

        boolean usernameExist = userService.findIfUsernameExist(userRegistrationDto);
        boolean emailExist = userService.findIfEmailExist(userRegistrationDto);

        if (usernameExist) {
            redirectAttributes.addFlashAttribute("usernameExist", true)
                    .addFlashAttribute("userRegistrationDto", userRegistrationDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:register";
        }

        if (emailExist) {
            redirectAttributes.addFlashAttribute("emailExist", true)
                    .addFlashAttribute("userRegistrationDto", userRegistrationDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:register";
        }

        userService.registerUser(userRegistrationDto);

        return "redirect:/";
    }

    @GetMapping("login")
    public String login(Model model) {
        if (!model.containsAttribute("errorLogin")) {
            model.addAttribute("errorLogin", false);
        }

        return "/login";
    }

    @PostMapping("login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        boolean loginSuccessful = userService.loginUser(userLoginDto);

        if (loginSuccessful && !bindingResult.hasErrors()) return "redirect:/home";

        redirectAttributes.addFlashAttribute("errorLogin", true)
                .addFlashAttribute("userLoginDto", userLoginDto)
                .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

        return  "redirect:login";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        userService.logout(httpSession);
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @ModelAttribute
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

}
