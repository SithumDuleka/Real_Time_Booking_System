package com.example.ticketbooking.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket configuration class.
 *
 * This class configures WebSocket endpoints and registers the {@link ActivityWebSocketHandler}
 * to handle incoming WebSocket connections and broadcast messages to clients.
 *
 * @EnableWebSocket: Enables WebSocket support in the Spring application.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * Injected instance of the {@link ActivityWebSocketHandler}.
     */
    private final ActivityWebSocketHandler activityWebSocketHandler;

    /**
     * Constructs a new WebSocketConfig instance.
     *
     * @param activityWebSocketHandler The WebSocket handler for processing activity updates.
     */
    public WebSocketConfig(ActivityWebSocketHandler activityWebSocketHandler) {
        this.activityWebSocketHandler = activityWebSocketHandler;
    }

    /**
     * Registers the WebSocket handler for the specified endpoint.
     *
     * This method maps the `/live-updates` endpoint to the {@link ActivityWebSocketHandler},
     * allowing clients to connect and receive real-time updates.
     *
     * @param registry The WebSocketHandlerRegistry to register handlers.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(activityWebSocketHandler, "/live-updates").setAllowedOrigins("*");
    }
}