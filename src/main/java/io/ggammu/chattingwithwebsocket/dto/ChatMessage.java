package io.ggammu.chattingwithwebsocket.dto;

import io.ggammu.chattingwithwebsocket.chat.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
