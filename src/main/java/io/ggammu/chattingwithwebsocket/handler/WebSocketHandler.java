package io.ggammu.chattingwithwebsocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggammu.chattingwithwebsocket.chat.ChatRoom;
import io.ggammu.chattingwithwebsocket.chat.ChatRoomRepository;
import io.ggammu.chattingwithwebsocket.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ChatRoomRepository chatRoomRepository;
    private ObjectMapper objectMapper;

//    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);
        log.info("connected : {}", session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("transfer message : {} : {}", session, message.getPayload());

        String msg = message.getPayload();
        ChatMessage chatMessage = objectMapper.convertValue(msg, ChatMessage.class);

        ChatRoom chatRoom = chatRoomRepository.findRoomById(chatMessage.getRoomId());
        chatRoom.handleMessage(session, chatMessage, objectMapper);

//        TextMessage msg = new TextMessage(message.getPayload());

//        sessions.forEach(s -> {
//            try {
//                s.sendMessage(msg);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.remove(session);
        log.info("disconnect : {}", session);
    }

}
