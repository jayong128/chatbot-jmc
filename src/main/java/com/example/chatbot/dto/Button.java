package com.example.chatbot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Button {
    private String action;
    private String label;
    private String messageText;

    public Button(String action, String label, String messageText) {
        this.action = action;
        this.label = label;
        this.messageText = messageText;
    }
}
