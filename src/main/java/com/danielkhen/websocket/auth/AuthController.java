package com.danielkhen.websocket.auth;

import com.danielkhen.websocket.dto.LoginRequestDTO;
import com.danielkhen.websocket.dto.LoginResponseDTO;
import com.danielkhen.websocket.dto.SignupRequestDTO;
import com.danielkhen.websocket.security.JwtTokenUtil;
import com.danielkhen.websocket.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class handling authentication-related endpoints.
 * This includes user registration and login functionalities.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Handles user registration.
     *
     * @param signupRequestDTO DTO containing user registration details
     * @return ResponseEntity containing the registered User object if successful
     */
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        User registeredUser = authService.signup(signupRequestDTO);
        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Handles user authentication (login).
     *
     * @param loginRequestDTO DTO containing login credentials
     * @return ResponseEntity containing LoginResponseDTO with JWT token and expiration time
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        // Authenticate the user
        User authenticatedUser = authService.authenticate(loginRequestDTO);

        // Generate JWT token
        String jwtToken = jwtTokenUtil.generateToken(authenticatedUser);

        // Create login response with token and expiration time
        LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtTokenUtil.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}