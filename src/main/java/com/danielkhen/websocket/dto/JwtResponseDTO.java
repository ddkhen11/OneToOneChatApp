package com.danielkhen.websocket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for JWT authentication response.
 * This class encapsulates the data sent back to the client after successful authentication.
 */
@Getter
@Setter
public class JwtResponseDTO {
    /**
     * The JWT token issued to the authenticated user.
     */
    private String token;

    /**
     * The type of token, typically "Bearer".
     */
    private String type = "Bearer";

    /**
     * The nickname of the authenticated user.
     */
    private String nickName;

    /**
     * The email of the authenticated user.
     */
    private String email;
}
