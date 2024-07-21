package com.danielkhen.websocket.chatroom;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a chat room between two users.
 * This entity is stored in MongoDB.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatRoom {
    /** Unique identifier for the chat room */
    @Id
    private String id;

    /** A composite ID representing the unique pairing of sender and recipient */
    private String chatId;

    /** ID of the user who initiated the chat or sent the first message */
    private String senderId;

    /** ID of the user who is receiving the message or participating in the chat */
    private String recipientId;
}
