package com.danielkhen.websocket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * Handles various exceptions and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ChatRoomNotFoundException and returns a 404 Not Found response.
     *
     * @param ex The caught ChatRoomNotFoundException
     * @return ResponseEntity with error details and 404 status
     */
    @ExceptionHandler(ChatRoomNotFoundException.class)
    public ResponseEntity<String> handleChatRoomNotFoundException(ChatRoomNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserNotFoundException and returns a 404 Not Found response.
     *
     * @param ex The caught UserNotFoundException
     * @return ResponseEntity with error details and 404 status
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserAlreadyExistsException and returns a 409 Conflict response.
     *
     * @param ex The caught UserAlreadyExistsException
     * @return ResponseEntity with error details and 409 status
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    /**
     * Handles validation errors (e.g., @Valid annotation failures).
     *
     * @param ex The caught MethodArgumentNotValidException
     * @return ResponseEntity with validation error details and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles authentication exceptions.
     *
     * @param ex The caught AuthenticationException
     * @return ResponseEntity with authentication error details and 401 status
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        String errorMessage = (ex instanceof BadCredentialsException)
                ? "Invalid username or password"
                : "Authentication failed";
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles general exceptions and returns a 500 Internal Server Error response.
     *
     * @param ex The caught Exception
     * @return ResponseEntity with error details and 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}