package com.danielkhen.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controller for handling chat-related operations.
 * Manages WebSocket messages and provides REST endpoints for chat history.
 */
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    /**
     * Handles incoming chat messages via WebSocket.
     * Saves the message and sends a notification to the recipient.
     *
     * @param message The incoming chat message
     */
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) {
        ChatMessage savedMessage = chatMessageService.save(message);
        messagingTemplate.convertAndSendToUser(
                savedMessage.getRecipientId(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMessage.getId())
                        .senderId(savedMessage.getSenderId())
                        .recipientId(savedMessage.getRecipientId())
                        .content(savedMessage.getContent())
                        .build()
        );
    }

    /**
     * REST endpoint to retrieve chat history between two users.
     *
     * @param senderId ID of the sender
     * @param recipientId ID of the recipient
     * @return List of chat messages between the sender and recipient
     */
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}