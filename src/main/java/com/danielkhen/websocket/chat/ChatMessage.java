package com.danielkhen.websocket.chat;

import com.danielkhen.websocket.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String chatId;

    /** ID of the user who sent this message */
    @NotBlank
    private String senderId;

    /** ID of the user who should receive this message */
    @NotBlank
    private String recipientId;

    /** Content of the message */
    @NotBlank
    @Size(max = AppConstants.MAX_MESSAGE_LENGTH)
    private String content;

    /** Timestamp when the message was sent */
    @NotNull
    private Date timestamp;
}