package com.masi.petparadise.chatbotEngine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.masi.petparadise.chatbotEngine.model.Conversation;

public interface RatingRepository extends MongoRepository<Conversation, String>{

}
