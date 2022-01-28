package io.ggammu.chattingwithwebsocket.controller;

import io.ggammu.chattingwithwebsocket.chat.ChatRoom;
import io.ggammu.chattingwithwebsocket.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatRoomRepository repository;

//    @GetMapping("/")
//    public String chat() {
//        return "chat";
//    }

    @GetMapping("/")
    public String chat(Model model) {
        model.addAttribute("rooms", repository.findAllRoom());
        return "rooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model) {
        ChatRoom room = repository.findRoomById(id);
        model.addAttribute("room", room);
        return "room";
    }

    @GetMapping("/new")
    public String make(Model model) {
        ChatRoomForm chatRoomForm = new ChatRoomForm();
        model.addAttribute("chatRoomForm", chatRoomForm);
        return "newRoom";
    }

}
