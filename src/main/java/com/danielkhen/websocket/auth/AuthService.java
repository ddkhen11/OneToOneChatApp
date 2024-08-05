package com.danielkhen.websocket.auth;

import com.danielkhen.websocket.dto.LoginRequestDTO;
import com.danielkhen.websocket.dto.SignupRequestDTO;
import com.danielkhen.websocket.exception.UserNotFoundException;
import com.danielkhen.websocket.user.User;
import com.danielkhen.websocket.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication-related operations.
 * This class provides methods for user signup and authentication.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user in the system.
     *
     * @param input The SignupRequestDTO containing user registration details.
     * @return The newly created User entity.
     */
    public User signup(SignupRequestDTO input) {
        // Create a new User entity from the input data
        User user = User.builder()
                .nickName(input.getNickName())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword())) // Encode the password before saving
                .build();

        // Save and return the new user
        return userRepository.save(user);
    }

    /**
     * Authenticates a user based on their credentials.
     *
     * @param input The LoginRequestDTO containing the user's login credentials.
     * @return The authenticated User entity.
     * @throws UserNotFoundException if the user is not found after successful authentication.
     */
    public User authenticate(LoginRequestDTO input) {
        // Attempt to authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getNickName(),
                        input.getPassword()
                )
        );

        // If authentication is successful, retrieve and return the user
        return userRepository.findByNickName(input.getNickName())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + input.getNickName()));
    }
}