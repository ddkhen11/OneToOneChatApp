package com.danielkhen.websocket.user;

import com.danielkhen.websocket.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing user operations.
 * Handles user connections, disconnections, and queries.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    /**
     * Saves a new user or updates an existing user's status to ONLINE.
     *
     * @param user The user to save
     */
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    /**
     * Updates a user's status to OFFLINE when they disconnect.
     *
     * @param user The user who is disconnecting
     * @throws UserNotFoundException if the user is not found
     */
    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + user.getNickName()));
        storedUser.setStatus(Status.OFFLINE);
        repository.save(storedUser);
    }

    /**
     * Retrieves all users with ONLINE status.
     *
     * @return List of online users
     */
    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}