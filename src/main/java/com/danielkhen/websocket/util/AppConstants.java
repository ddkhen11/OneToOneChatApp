package com.danielkhen.websocket.util;

/**
 * This class contains application-wide constants.
 * It provides a centralized location for all magic numbers and string literals used across the application.
 */
public final class AppConstants {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private AppConstants() {
        throw new AssertionError("AppConstants class should not be instantiated.");
    }

    // JWT related constants
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    // User related constants
    public static final int MAX_NICKNAME_LENGTH = 20;
    public static final int MAX_FIRSTNAME_LENGTH = 20;
    public static final int MAX_LASTNAME_LENGTH = 20;
    public static final int MAX_EMAIL_LENGTH = 50;
    public static final int MAX_PASSWORD_LENGTH = 120;

    // Chat related constants
    public static final int MAX_MESSAGE_LENGTH = 500;
}