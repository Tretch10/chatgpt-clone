package com.example.chatgptclone.controller;

import com.example.chatgptclone.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final List<String> history = new LinkedList<>();

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String chatPage(Model model) {
        model.addAttribute("history", history);
        return "chat";
    }

    @PostMapping
    public String sendPrompt(@RequestParam String prompt, Model model) {
        String response = chatService.ask(prompt);
        history.add("You: " + prompt);
        history.add("ChatGPT: " + response);
        model.addAttribute("history", history);
        return "chat";
    }
}
