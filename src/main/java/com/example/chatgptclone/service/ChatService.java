package com.example.chatgptclone.service;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final OpenAiChatClient chatClient;

    public ChatService(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String ask(String prompt) {
        return chatClient.call(prompt);
    }
}
