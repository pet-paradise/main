package com.masi.petparadise.watsonCommunication.controller;

import com.masi.petparadise.chatbotEngine.model.Conversation;
import com.masi.petparadise.watsonCommunication.controller.DTO.Message;
import com.masi.petparadise.watsonCommunication.controller.DTO.Metric;
import com.masi.petparadise.watsonCommunication.controller.DTO.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masi.petparadise.watsonCommunication.service.WatsonCommunication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		return new ResponseEntity<>(watsonCommunication.communicateV2(message), HttpStatus.OK);
	}
	
	@PostMapping("/rate")
	public ResponseEntity<?> rate(@RequestBody Rating rating){
		Conversation conversation = watsonCommunication.saveRating(rating);
		List<Map.Entry<String, Double>> tempList = new ArrayList<>(conversation.getQEI().entrySet());
		Metric metric = new Metric();
		metric.setACL(conversation.getACL());
		metric.setCES(conversation.getCes());
		metric.setCFI(conversation.getCFI());
		metric.setCUS(conversation.getCus());
		metric.setQEI(tempList.get(tempList.size() - 1).getValue());
		//System.out.println(rating.getConversationId() + " ---- " + rating.getCes() + "   " + rating.getCus());
		return new ResponseEntity<>(metric, HttpStatus.OK);
		
	}
}
