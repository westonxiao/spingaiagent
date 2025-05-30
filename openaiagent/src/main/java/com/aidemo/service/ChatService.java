package com.aidemo.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getChatResponse(String message) {
        // Simple call
        return chatClient.call(message);
    }

    public String getFormattedResponse(String topic) {
        // Using prompt templates
        String systemMessage = """
            You are a helpful assistant that provides information about {topic}.
            Your responses should be clear, concise, and formatted for easy reading.
            """;

        SystemPromptTemplate promptTemplate = new SystemPromptTemplate(systemMessage);
        Prompt prompt = promptTemplate.create(Map.of("topic", topic));

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}