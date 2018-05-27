package com.masi.petparadise.watsonCommunication.service;


import com.ibm.watson.developer_cloud.assistant.v1.model.*;
import com.masi.petparadise.chatbotEngine.model.PetSupplyItem;
import com.masi.petparadise.chatbotEngine.service.AmazonItemService;
import com.masi.petparadise.watsonCommunication.controller.DTO.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;


import java.util.List;

@Service
public class WatsonCommunication {
	
	private final String workspaceId = "a771707b-1ef5-4789-904d-48d15dd01745";
	private Assistant service;

	@Autowired
	public WatsonCommunication() {
		this.service = new Assistant("2018-02-16");
		this.service.setUsernameAndPassword("02bd8aee-854a-44e0-a80d-5cb030ed5302", "17hDwxvu46Xs");
	}
	
	private MessageResponse startConversation(){
		MessageOptions options = new MessageOptions.Builder(workspaceId).build();
	    MessageResponse response = service.message(options).execute();

	    return response;
	}

	public Message communicate(Message message){
		Context context = message.getContext();
		MessageResponse response;

		if (context == null) {
			response = startConversation();
		}
		else {
			InputData input = new InputData.Builder(message.getMessage()).build();
			MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
			response = service.message(options).execute();
		}

		Object isConversationFinished = response.getContext().get("isConversationFinished");

		if (isConversationFinished != null) {
			//return products
			System.out.println(response.getOutput().getText().get(0));
			return prepareViewMessage(response.getOutput().getText().get(0), getProducts(prepareKeywords(response.getEntities())), context);
		}
		else {
			System.out.println(response.getOutput().getText().get(0));
			context = response.getContext();
			return prepareViewMessage(response.getOutput().getText().get(0), null, context);
		}
	}

	private List<PetSupplyItem> getProducts(String keywords) {
		AmazonItemService itemService = new AmazonItemService();
		return itemService.SearchItemByKeywords(keywords);
	}

	private String prepareKeywords(List<RuntimeEntity> entities) {
		String keywords = "";
		for(RuntimeEntity entity : entities) {
			keywords += entity.getValue() + " ";
		}
		return keywords;
	}

	private Message prepareViewMessage(String textMessage, List<PetSupplyItem> items, Context context) {
		Message message = new Message();
		message.setMessage(textMessage);
		message.setSupplyItems(items);
		message.setContext(context);
		return message;
	}
}
