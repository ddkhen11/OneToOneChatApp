package com.danielkhen.websocket.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing the response for a successful login.
 * This class encapsulates the JWT token and its expiration time.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    /**
     * The JWT token issued upon successful authentication.
     */
    private String token;

    /**
     * The expiration time of the token in seconds.
     */
    private long expiresIn;
}
