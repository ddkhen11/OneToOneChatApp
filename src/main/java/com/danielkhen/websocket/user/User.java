package com.danielkhen.websocket.user;


import com.danielkhen.websocket.util.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user in the chat system.
 * This entity is stored in MongoDB.
 */
@Getter
@Setter
@Document(collection = "users")
public class User {
    /** Unique identifier for the user, also serves as their nickname */
    @Id
    @NotBlank
    @Size(max = AppConstants.MAX_NICKNAME_LENGTH)
    private String nickName;

    /** The first name of the user */
    @NotBlank
    @Size(max = AppConstants.MAX_FIRSTNAME_LENGTH)
    private String firstName;

    /** The last name of the user */
    @NotBlank
    @Size(max = AppConstants.MAX_LASTNAME_LENGTH)
    private String lastName;

    /** The email of the user */
    @NotBlank
    @Size(max = AppConstants.MAX_EMAIL_LENGTH)
    @Email
    private String email;

    /** The password of the user */
    @NotBlank
    @Size(max = AppConstants.MAX_PASSWORD_LENGTH)
    private String password;

    /** The current status of the user (online/offline) */
    @NotBlank
    private Status status;
}