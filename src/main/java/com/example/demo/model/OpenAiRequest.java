package com.example.demo.model;

import java.util.List;

public class OpenAiRequest {
    private String model;
    private List<OpenAiMessage> messages;

    public OpenAiRequest(String model, List<OpenAiMessage> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<OpenAiMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<OpenAiMessage> messages) {
        this.messages = messages;
    }
}
