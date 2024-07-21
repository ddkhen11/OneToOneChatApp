package com.danielkhen.websocket.chat;

import com.danielkhen.websocket.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing chat messages.
 * Handles saving new messages and retrieving message history.
 */
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    /**
     * Saves a new chat message.
     * If a chat room doesn't exist for the sender and recipient, it creates one.
     *
     * @param chatMessage The message to be saved
     * @return The saved chat message
     */
    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();
        chatMessage.setChatId(chatId);
        return chatMessageRepository.save(chatMessage);
    }

    /**
     * Retrieves the chat history between two users.
     *
     * @param senderId ID of the sender
     * @param recipientId ID of the recipient
     * @return List of chat messages, or an empty list if no chat room exists
     */
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}