package com.danielkhen.websocket.dto;

import com.danielkhen.websocket.util.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for user signup requests.
 * This class encapsulates the data required for user registration.
 */
@Getter
@Setter
public class SignupRequestDTO {
    /**
     * The user's chosen nickname. Must not be blank and have a maximum length of 20 characters.
     */
    @NotBlank(message = "Nickname is required")
    @Size(max = AppConstants.MAX_NICKNAME_LENGTH, message = "Nickname can be at most " + AppConstants.MAX_NICKNAME_LENGTH + " characters long")
    private String nickName;

    /**
     * The user's first name. Must not be blank and have a maximum length of 20 characters.
     */
    @NotBlank(message = "First name is required")
    @Size(max = AppConstants.MAX_FIRSTNAME_LENGTH, message = "First name can be at most " + AppConstants.MAX_FIRSTNAME_LENGTH + " characters long")
    private String firstName;

    /**
     * The user's last name. Must not be blank and have a maximum length of 20 characters.
     */
    @NotBlank(message = "Last name is required")
    @Size(max = AppConstants.MAX_LASTNAME_LENGTH, message = "Last name can be at most " + AppConstants.MAX_LASTNAME_LENGTH + " characters long")
    private String lastName;

    /**
     * The user's email address. Must be a valid email format and have a maximum length of 50 characters.
     */
    @NotBlank(message = "Email is required")
    @Size(max = AppConstants.MAX_EMAIL_LENGTH, message = "Email can be at most " + AppConstants.MAX_EMAIL_LENGTH + " characters long")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The user's password. Must not be blank and have a maximum length of 120 characters.
     */
    @NotBlank(message = "Password is required")
    @Size(max = AppConstants.MAX_PASSWORD_LENGTH, message = "Password can be at most " + AppConstants.MAX_PASSWORD_LENGTH + " characters long")
    private String password;
}
