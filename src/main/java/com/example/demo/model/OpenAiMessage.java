package com.example.demo.model;

public class OpenAiMessage {
    private String role;
    private String content;

    public OpenAiMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}