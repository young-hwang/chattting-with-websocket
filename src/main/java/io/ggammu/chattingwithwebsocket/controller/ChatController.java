package io.ggammu.chattingwithwebsocket.controller;

import io.ggammu.chattingwithwebsocket.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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

        return "room";
    }

}
