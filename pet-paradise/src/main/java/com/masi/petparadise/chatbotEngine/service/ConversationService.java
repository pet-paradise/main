package com.masi.petparadise.chatbotEngine.service;

import com.masi.petparadise.chatbotEngine.model.Conversation;
import com.masi.petparadise.chatbotEngine.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConversationService {

    private final ConversationRepository repository;

    public Conversation findByConversationId(String conversationId) {
        return repository.findConversationByConversationId(conversationId);
    }

}
