package com.danielkhen.websocket.exception;

/**
 * Exception thrown when a requested chat room is not found.
 */
public class ChatRoomNotFoundException extends RuntimeException {

    /**
     * Constructs a new ChatRoomNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ChatRoomNotFoundException(String message) {
        super(message);
    }
}