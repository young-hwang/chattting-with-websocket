package io.ggammu.chattingwithwebsocket.controller;

import io.ggammu.chattingwithwebsocket.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
