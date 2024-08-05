package com.danielkhen.websocket.user;

import com.danielkhen.websocket.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing user operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Saves a new user with an encoded password.
     *
     * @param user The user to save
     */
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Updates an existing user's status to ONLINE.
     *
     * @param user The user to save
     */
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    /**
     * Updates a user's status to OFFLINE when they disconnect.
     *
     * @param user The user who is disconnecting
     * @throws UserNotFoundException if the user is not found
     */
    public void disconnect(User user) {
        var storedUser = userRepository.findById(user.getNickName())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + user.getNickName()));
        storedUser.setStatus(Status.OFFLINE);
        userRepository.save(storedUser);
    }

    /**
     * Retrieves all users with ONLINE status.
     *
     * @return List of online users
     */
    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}