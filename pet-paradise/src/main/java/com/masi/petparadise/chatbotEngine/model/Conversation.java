package com.masi.petparadise.chatbotEngine.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import com.ibm.watson.developer_cloud.assistant.v1.model.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Conversation {

    @Id
    private String conversationId;

    private Context context;

    private List<String> messages;

    private List<String> entities;

    private boolean isConversationFinished;

    public Conversation() {
        messages = new ArrayList<>();
        entities = new ArrayList<>();
    }
}
