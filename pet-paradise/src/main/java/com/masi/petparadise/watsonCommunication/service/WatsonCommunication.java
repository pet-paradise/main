package com.masi.petparadise.watsonCommunication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@Service
public class WatsonCommunication {

	private ConversationService conversationService;
	
	@Autowired
	public WatsonCommunication() {
		this.conversationService = new ConversationService(ConversationService.VERSION_DATE_2017_02_03); 
		//setUsername and you Password inside your Conversation Service (Service Credentials)
		conversationService.setUsernameAndPassword("02bd8aee-854a-44e0-a80d-5cb030ed5302", "17hDwxvu46Xs");
	}

	public String communicate(String message){
		MessageRequest newMessage = new MessageRequest.Builder().inputText(message).build();
		MessageResponse response = conversationService.message("a771707b-1ef5-4789-904d-48d15dd01745", newMessage).execute();
		return response.getText().get(0);
	}
	
}
