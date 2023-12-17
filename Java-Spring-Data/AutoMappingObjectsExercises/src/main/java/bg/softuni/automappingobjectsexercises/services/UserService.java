package bg.softuni.automappingobjectsexercises.services;

import bg.softuni.automappingobjectsexercises.models.dto.UserLoginDto;
import bg.softuni.automappingobjectsexercises.models.dto.UserRegistrationDto;

public interface UserService {

    void registerUser(UserRegistrationDto userRegistrationDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean hasLoggedInUser();

    boolean hasUserAreAdministrator();

    void getOwnedGames();
}
