package io.ggammu.chattingwithwebsocket.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggammu.chattingwithwebsocket.dto.ChatMessage;
import lombok.Builder;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper) throws IOException {
        if (chatMessage.getType() == MessageType.ENTER) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }
        if (chatMessage.getType() == MessageType.CHAT) {
            chatMessage.setMessage(chatMessage.getSender() + " : " + chatMessage.getMessage());
        }
        if (chatMessage.getType() == MessageType.LEAVE) {
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getMessage() + "님이 떠났습니다.");
        }
        send(chatMessage);
    }

    private void send(ChatMessage chatMessage) {
        TextMessage textMessage = new TextMessage(chatMessage.getMessage());

        sessions.forEach(
                session -> {
                    try {
                        session.sendMessage(textMessage);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }
        );

    }

}
