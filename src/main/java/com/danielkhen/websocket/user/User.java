package com.danielkhen.websocket.user;


import com.danielkhen.websocket.util.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Represents a user in the chat system.
 * This entity is stored in MongoDB.
 */
@Getter
@Setter
@Document(collection = "users")
@Builder
public class User implements UserDetails {
    /** Unique identifier for the user, also serves as their nickname */
    @Id
    @NotBlank
    @Size(max = AppConstants.MAX_NICKNAME_LENGTH)
    @Indexed(unique = true)
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
    @Indexed(unique = true)
    private String email;

    /** The password of the user */
    @NotBlank
    private String password;

    /** The current status of the user (online/offline) */
    @NotBlank
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return nickName;
    }
}