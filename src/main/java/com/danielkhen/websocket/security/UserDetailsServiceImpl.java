package com.danielkhen.websocket.security;

import com.danielkhen.websocket.user.User;
import com.danielkhen.websocket.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Implementation of UserDetailsService to integrate with Spring Security.
 * This service loads user-specific data and creates a UserDetails object that Spring Security can use for authentication and validation.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Load a user by nickname.
     *
     * @param nickName the nickname of the user to load
     * @return a UserDetails object containing the user's information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userRepository.findByNickName(nickName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with nickname: " + nickName));

        return buildUserDetails(user);
    }

    /**
     * Constructs a UserDetails object from our custom User entity.
     *
     * @param user the User entity
     * @return a UserDetails object
     */
    private UserDetails buildUserDetails(User user) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        return new org.springframework.security.core.userdetails.User(
                user.getNickName(),
                user.getPassword(),
                Collections.singletonList(authority));
    }
}