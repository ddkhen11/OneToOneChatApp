package com.danielkhen.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

/**
 * Configuration class for WebSocket communication.
 * Sets up WebSocket endpoints and message brokers.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker for WebSocket communication.
     * This method sets up the destinations for the broker and the application.
     *
     * @param registry The MessageBrokerRegistry to configure
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker with the /user prefix
        // This is where messages will be broadcast from
        registry.enableSimpleBroker("/user");

        // Set the prefix for messages sent from client to server-side methods
        // Client will need to prepend this to any destinations
        registry.setApplicationDestinationPrefixes("/app");

        // Set the prefix for user-specific messages
        // This is used for sending messages to specific users
        registry.setUserDestinationPrefix("/user");
    }

    /**
     * Registers STOMP endpoints for WebSocket communication.
     * This method sets up the endpoint that the clients will use to connect to our WebSocket server.
     *
     * @param registry The StompEndpointRegistry to configure
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registers an endpoint for WebSocket connections at /ws and enables SockJS fallback options
        // SockJS is used for browsers that don't support WebSocket
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * Configures the message converters for WebSocket communication.
     * This method sets up JSON as the default message format.
     *
     * @param messageConverters The list of message converters to configure
     * @return true to allow other converters to be added, false otherwise
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // Sets up JSON as the default message format
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(APPLICATION_JSON);

        // Creates a JSON message converter
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);

        // Adds the JSON message converter to the list of message converters
        messageConverters.add(converter);

        // Return true to allow other converters to be added if needed
        return true;
    }
}