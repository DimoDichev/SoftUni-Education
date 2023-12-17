package bg.softuni.automappingobjectsexercises.models.dto;

import bg.softuni.automappingobjectsexercises.common.ConstantMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UserRegistrationDto implements UserDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegistrationDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    @Email(message = ConstantMessages.INCORRECT_EMAIL_INSERT)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$",
            message = ConstantMessages.INCORRECT_PASSWORD_INSERT)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
