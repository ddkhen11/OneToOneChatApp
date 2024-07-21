package com.danielkhen.websocket.user;

import lombok.RequiredArgsConstructor;
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
        service.saveUser(user);
        return user;
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
        service.disconnect(user);
        return user;
    }

    /**
     * REST endpoint to retrieve all connected users.
     *
     * @return List of connected users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }
}