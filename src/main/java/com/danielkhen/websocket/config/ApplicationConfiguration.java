package com.danielkhen.websocket.config;

import com.danielkhen.websocket.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for application-wide beans related to security and authentication.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    /**
     * Creates a UserDetailsService bean.
     * This service is used by Spring Security to load user-specific data during authentication.
     *
     * @return A UserDetailsService implementation that fetches user details from the UserRepository.
     */
    @Bean
    UserDetailsService userDetailsService() {
        return nickName -> userRepository.findByNickName(nickName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with nickname: " + nickName));
    }

    /**
     * Creates a BCryptPasswordEncoder bean.
     * This encoder is used for hashing passwords before storing them and for password verification.
     *
     * @return A new instance of BCryptPasswordEncoder.
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an AuthenticationManager bean.
     * The AuthenticationManager is responsible for processing authentication requests.
     *
     * @param config The AuthenticationConfiguration used to create the AuthenticationManager.
     * @return An instance of AuthenticationManager.
     * @throws Exception if there's an error creating the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Creates an AuthenticationProvider bean.
     * This provider is responsible for authenticating users based on the UserDetailsService and PasswordEncoder.
     *
     * @return A configured DaoAuthenticationProvider.
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}