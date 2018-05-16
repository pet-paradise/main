package com.masi.petparadise.watsonCommunication.controller.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Conversation {
    private List<String> messages;
    private String conversationId;

    public Conversation() {
        messages = new ArrayList<>();
    }
}

