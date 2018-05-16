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
	
	/*@GetMapping("/message/{message}")
	public ResponseEntity<?> communicate(@PathVariable String message){
		String responseMessage = watsonCommunication.communicate(message);
		System.out.println(responseMessage);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}*/

	@PostMapping("/message")
	public ResponseEntity<?> communicate(@RequestBody Message message){
		Conversation conversation = new Conversation();
		conversation.getMessages().add(message.getMessage());

		//trzeba obsłużyć conversationId - nie może być ustawiane przy każdym requescie
		conversation.setConversationId(message.getConversationId());

		message.setMessage(watsonCommunication.communicate(message.getMessage()));
		System.out.println(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
