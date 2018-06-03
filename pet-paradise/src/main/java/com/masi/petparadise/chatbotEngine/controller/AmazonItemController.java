package com.masi.petparadise.chatbotEngine.controller;

import com.masi.petparadise.chatbotEngine.model.Conversation;
import com.masi.petparadise.chatbotEngine.repository.ConversationRepository;
import com.masi.petparadise.chatbotEngine.service.AmazonItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path="/api/amazon")
public class AmazonItemController {

    private final AmazonItemService amazonItemService;

    private final ConversationRepository conversationRepository;

    @GetMapping("/keywords/{keywords}")
    public ResponseEntity<?> GetItemsByCategory(@PathVariable String keywords) {
        return new ResponseEntity<>(amazonItemService.SearchItemByKeywords(keywords), HttpStatus.OK);
    }

    /*@GetMapping("")
    public ResponseEntity<?> GetAllConversations() {

        conversationRepository.deleteAll();

        Conversation conversation1 = new Conversation();
        conversation1.setConversationId("1");
        conversation1.setMessage("test");

        Conversation conversation2 = new Conversation();
        conversation2.setMessage("test2");
        conversation2.setConversationId("2");

        conversationRepository.save(conversation1);
        conversationRepository.save(conversation2);

        return new ResponseEntity<>(conversationRepository.findAll(), HttpStatus.OK);
    }*/
}
