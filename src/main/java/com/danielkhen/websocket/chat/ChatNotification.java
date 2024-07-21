package com.danielkhen.websocket.chat;

import lombok.*;

/**
 * Represents a notification for a new chat message.
 * This is used to inform recipients of new messages in real-time.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    /** Unique identifier for the message this notification is for  */
    private String id;

    /** ID of the user who sent the message */
    private String senderId;

    /** ID of the user who should receive the message */
    private String recipientId;

    /** Content of the notification */
    private String content;
}
