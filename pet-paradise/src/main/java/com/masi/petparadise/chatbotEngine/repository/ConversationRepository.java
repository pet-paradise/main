package com.masi.petparadise.chatbotEngine.repository;

import com.masi.petparadise.chatbotEngine.model.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

    Conversation findConversationByConversationId(String conversationId);
}
