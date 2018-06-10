package com.masi.petparadise.chatbotEngine.service;

import com.masi.petparadise.chatbotEngine.model.Conversation;
import com.masi.petparadise.chatbotEngine.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConversationService {

    private final ConversationRepository repository;

    public Conversation findByConversationId(String conversationId) {
        if (conversationId == null)
            return null;
        else
            return repository.findConversationByConversationId(conversationId);
    }

    public Conversation saveConversation(Conversation conversation) {
        return repository.save(conversation);
    }

    public Conversation updateConversation(Conversation conversation) {
        Conversation conversationFromDb = repository.findConversationByConversationId(conversation.getConversationId());
        conversationFromDb.setContext(conversation.getContext());
        conversationFromDb.setMessages(conversation.getMessages());
        conversationFromDb.setConversationFinished(conversation.isConversationFinished());
        return conversationFromDb;
    }

    public List<String> addMessage(Conversation conversation, String message) {
        List<String> messages = conversation.getMessages();
        return messages;
    }

    public List<String> addEntity(Conversation conversation, String entity) {
        List<String> entities = conversation.getEntities();
        if (entities.contains(entity) == false)
            entities.add(entity);
        return entities;
    }

}
