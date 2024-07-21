package com.danielkhen.websocket.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user in the chat system.
 * This entity is stored in MongoDB.
 */
@Getter
@Setter
@Document
public class User {
    /** Unique identifier for the user, also serves as their nickname */
    @Id
    private String nickName;

    /** The full name of the user */
    private String fullName;

    /** The current status of the user (online/offline) */
    private Status status;
}
