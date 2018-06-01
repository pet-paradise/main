package com.masi.petparadise.chatbotEngine.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Conversation {

    @Id
    private String conversationId;
    private String message;

    @Override
    public String toString() {
        return String.format("ConversationId='%s', message='%s'", conversationId, message);
    }
}
