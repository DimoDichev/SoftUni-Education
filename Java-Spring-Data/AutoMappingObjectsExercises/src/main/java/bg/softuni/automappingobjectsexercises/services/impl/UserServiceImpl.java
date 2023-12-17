package bg.softuni.automappingobjectsexercises.services.impl;

import bg.softuni.automappingobjectsexercises.common.ConstantMessages;
import bg.softuni.automappingobjectsexercises.models.dto.UserDto;
import bg.softuni.automappingobjectsexercises.models.dto.UserLoginDto;
import bg.softuni.automappingobjectsexercises.models.dto.UserRegistrationDto;
import bg.softuni.automappingobjectsexercises.models.entities.Game;
import bg.softuni.automappingobjectsexercises.models.entities.User;
import bg.softuni.automappingobjectsexercises.repositories.UserRepository;
import bg.softuni.automappingobjectsexercises.services.UserService;
import bg.softuni.automappingobjectsexercises.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loginUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            System.out.println(ConstantMessages.INCORRECT_VALIDATION_PASSWORD);
            return;
        }

        if (checkValidations(userRegistrationDto)) return;

        if (userRepository.findByEmail(userRegistrationDto.getEmail()).isPresent()) {
            System.out.println(ConstantMessages.EXISTING_EMAIL);
            return;
        }

        userRepository.save(modelMapper.map(userRegistrationDto, User.class));
        System.out.printf(ConstantMessages.SUCCESSFUL_REGISTRATION, userRegistrationDto.getFullName());
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        if (checkValidations(userLoginDto)) return;

        User user = userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if (user == null) {
            System.out.println(ConstantMessages.INCORRECT_USER_PASSWORD);
            return;
        }

        if (this.loginUser != null) {
            System.out.println(ConstantMessages.ALREADY_HAVE_LOGIN_USER);
            return;
        }

        this.loginUser = user;
        System.out.printf(ConstantMessages.LOGIN_SUCCESSFUL, user.getFullName());
    }

    @Override
    public void logout() {
        if (this.loginUser == null) {
            System.out.println(ConstantMessages.LOGOUT_ERROR);
        } else {
            System.out.printf(ConstantMessages.LOGOUT_SUCCESSFUL, this.loginUser.getFullName());
            this.loginUser = null;
        }
    }

    @Override
    public void getOwnedGames() {
        if (this.loginUser == null) {
            System.out.println(ConstantMessages.NO_LOGGED_IN_USER);
            return;
        }

        loginUser.getGames().stream().map(Game::getTitle).forEach(System.out::println);
    }

    @Override
    public boolean hasLoggedInUser() {
        return loginUser != null;
    }

    @Override
    public boolean hasUserAreAdministrator() {
        return loginUser.isAdmin();
    }

    private boolean checkValidations(UserDto userDto) {
        Set<ConstraintViolation<UserDto>> violations = validationUtil.violations(userDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return true;
        }
        return false;
    }

}
