package com.masi.petparadise.watsonCommunication.controller;

import com.masi.petparadise.watsonCommunication.controller.DTO.Conversation;
import com.masi.petparadise.watsonCommunication.controller.DTO.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masi.petparadise.watsonCommunication.service.WatsonCommunication;

@RestController
@RequestMapping(path="/watson")
public class WatsonCommunicationController {

	@Autowired
	WatsonCommunication watsonCommunication;

	@PostMapping("/message")
	public ResponseEntity<?> communicate(@RequestBody Message message){
		//sprawdzanie czy jest ustawiony conversationId, tak => poczatek rozmowy, nie => kontynuacja
		/*Message responseMessage = new Message();
		if (message.getConversationId() == "null" || message.getConversationId() == null){
			System.out.println("--------Początek rozmowy---------");
			responseMessage = watsonCommunication.startConversation(message);
		} else{
			responseMessage.setConversationId(message.getConversationId());
			responseMessage.setMessage(watsonCommunication.communicate(message.getMessage(), message.getConversationId()));
		}*/
		return new ResponseEntity<>(watsonCommunication.communicate(message), HttpStatus.OK);
	}
}
