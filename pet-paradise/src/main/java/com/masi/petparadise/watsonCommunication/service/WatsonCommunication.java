package com.masi.petparadise.watsonCommunication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.masi.petparadise.watsonCommunication.controller.DTO.Message;

@Service
public class WatsonCommunication {
	
	private final String workspaceId = "a771707b-1ef5-4789-904d-48d15dd01745";
	private Assistant service;
	private MessageOptions options;
	private Context context;
	
	@Autowired
	public WatsonCommunication() {
		this.service = new Assistant("2018-02-16");
		this.service.setUsernameAndPassword("02bd8aee-854a-44e0-a80d-5cb030ed5302", "17hDwxvu46Xs");
		this.context = new Context();
	}
	
	public Message startConversation(Message message){
		MessageOptions options = new MessageOptions.Builder(workspaceId).build();
	    MessageResponse response = service.message(options).execute();
	    this.context = response.getContext();
	    Message responseMessage = new Message();
	    responseMessage.setMessage(response.getOutput().getText().get(0));
	    responseMessage.setConversationId(response.getContext().getConversationId());
	    return responseMessage;
	}

	public String communicate(String message, String conversationId){
		
		this.context.setConversationId(conversationId);
		InputData input = new InputData.Builder(message).build();
		options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		
	    MessageResponse response = service.message(options).execute();
	    System.out.println(response.getOutput().getText().get(0));
	    return response.getOutput().getText().get(0);
	}
	
}
