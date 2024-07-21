package com.danielkhen.websocket.chat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Represents a chat message in the system.
 * This entity is stored in MongoDB.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ChatMessage {
    /** Unique identifier for the message */
    @Id
    private String id;

    /** Unique identifier for the chat room this message belongs to */
    private String chatId;

    /** ID of the user who sent this message */
    private String senderId;

    /** ID of the user who should receive this message */
    private String recipientId;

    /** Content of the message */
    private String content;

    /** Timestamp when the message was sent */
    private Date timestamp;
}