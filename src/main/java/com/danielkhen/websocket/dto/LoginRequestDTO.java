package com.danielkhen.websocket.dto;

import com.danielkhen.websocket.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for user login requests.
 * This class encapsulates the data required for a user to login.
 */
@Getter
@Setter
public class LoginRequestDTO {
    /**
     * The user's chosen nickname. Must not be blank and have a maximum length of 20 characters.
     */
    @NotBlank(message = "Nickname is required")
    @Size(max = AppConstants.MAX_NICKNAME_LENGTH, message = "Nickname can be at most " + AppConstants.MAX_NICKNAME_LENGTH + " characters long")
    private String nickName;

    /**
     * The user's password. Must not be blank and have a maximum length of 120 characters.
     */
    @NotBlank(message = "Password is required")
    @Size(max = AppConstants.MAX_PASSWORD_LENGTH, message = "Password can be at most " + AppConstants.MAX_PASSWORD_LENGTH + " characters long")
    private String password;
}
