package com.danielkhen.websocket.user;

import com.danielkhen.websocket.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller for handling user-related operations.
 * Manages user connections, disconnections, and provides a REST endpoint for user queries.
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * Handles user connection via WebSocket.
     *
     * @param user The connecting user
     * @return The connected user
     */
    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload User user) {
        try {
            service.saveUser(user);
            return user;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error adding user: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handles user disconnection via WebSocket.
     *
     * @param user The disconnecting user
     * @return The disconnected user
     */
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User user) {
        try {
            service.disconnect(user);
            return user;
        } catch (UserNotFoundException e) {
            // Log the error
            System.err.println("Error disconnecting user: " + e.getMessage());
            return null;
        }
    }

    /**
     * REST endpoint to retrieve all connected users.
     *
     * @return List of connected users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        try {
            List<User> users = service.findConnectedUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error retrieving connected users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}