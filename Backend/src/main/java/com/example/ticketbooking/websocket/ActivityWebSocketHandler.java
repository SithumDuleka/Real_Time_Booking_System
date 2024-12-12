package com.example.ticketbooking.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * Websocket handler for broadcasting activity messages in the ticket booking simulation.
 *
 * This class handles WebSocket connections, broadcasts messages to connected clients,
 * and provides methods for handling connection establishment, closure, and message reception.
 *
 * @Component: Marks this class as a Spring managed component.
 * @TextWebSocketHandler: Indicates that this class handles text-based WebSocket messages.
 */
@Component
public class ActivityWebSocketHandler extends TextWebSocketHandler {

    /**
     * A thread-safe list to store connected WebSocket sessions.
     */
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    /**
     * Invoked after a WebSocket connection is established.
     *
     * Adds the session to the list of connected sessions and logs a message.
     *
     * @param session The established WebSocket session.
     * @throws Exception If there's an error during connection establishment.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("WebSocket connection established.");
    }

    /**
     * Invoked after a WebSocket connection is closed.
     *
     * Removes the session from the list and logs a message.
     *
     * @param session The closed WebSocket session.
     * @param status  The status of the closed connection.
     * @throws Exception If there's an error during connection closure.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("WebSocket connection closed.");
    }

    /**
     * Handles incoming text messages from connected clients.
     *
     * Currently, this method logs the received message for demonstration purposes.
     * You can implement additional logic to process incoming messages as needed.
     *
     * @param session The session that sent the message.
     * @param message The received text message.
     * @throws Exception If there's an error processing the message.
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
    }

    /**
     * Broadcasts a message to all connected clients.
     *
     * Iterates through the list of sessions and sends the provided message using TextMessage.
     *
     * @param message The message to broadcast.
     */
    public void broadcastMessage(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}