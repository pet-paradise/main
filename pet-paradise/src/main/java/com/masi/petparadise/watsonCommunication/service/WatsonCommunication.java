package com.masi.petparadise.watsonCommunication.service;


import com.ibm.watson.developer_cloud.assistant.v1.model.*;
import com.masi.petparadise.chatbotEngine.model.Conversation;
import com.masi.petparadise.chatbotEngine.model.PetSupplyItem;
import com.masi.petparadise.chatbotEngine.service.AmazonItemService;
import com.masi.petparadise.chatbotEngine.service.ConversationService;
import com.masi.petparadise.watsonCommunication.controller.DTO.Message;
import com.masi.petparadise.watsonCommunication.controller.DTO.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;


import java.util.List;

@Service
public class WatsonCommunication {
	
	private final String workspaceId = "a771707b-1ef5-4789-904d-48d15dd01745";
	private Assistant service;

	private final ConversationService conversationService;

	@Autowired
	public WatsonCommunication(ConversationService conversationService) {
		this.service = new Assistant("2018-02-16");
		this.service.setUsernameAndPassword("02bd8aee-854a-44e0-a80d-5cb030ed5302", "17hDwxvu46Xs");
		this.conversationService = conversationService;
	}
	
	private MessageResponse startConversation(){
		MessageOptions options = new MessageOptions.Builder(workspaceId).build();
	    MessageResponse response = service.message(options).execute();

	    return response;
	}

	/*public Message communicate(Message message){
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
	}*/

	private List<PetSupplyItem> getProducts(String keywords) {
		AmazonItemService itemService = new AmazonItemService();
		return itemService.SearchItemByKeywords(keywords);
	}

	private String prepareKeywords(List<String> entities) {
		String keywords = "";
		for(String entity : entities) {
			keywords += entity + " ";
		}
		return keywords;
	}

	private Message prepareViewMessage(String textMessage, List<PetSupplyItem> items, String conversationId) {
		Message message = new Message();
		message.setMessage(textMessage);
		message.setSupplyItems(items);
		message.setConversationId(conversationId);
		return message;
	}

	public Message communicateV2(Message message) {
		MessageResponse response;
		Conversation conversation = conversationService.findByConversationId(message.getConversationId());
		if (conversation == null || conversation.isConversationFinished() == true) {
			conversation = new Conversation();
			conversation.setMessages(conversationService.addMessage(conversation, message.getMessage()));
			response = startConversation();
		}
		else {
			//add user message to conversation
			conversation.setMessages(conversationService.addMessage(conversation, message.getMessage()));

			InputData input = new InputData.Builder(message.getMessage()).build();
			MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(conversation.getContext()).build();
			response = service.message(options).execute();
		}
		//update conversation from db
		updateConversation(conversation, response.getContext().getConversationId(), response.getOutput().getText().get(0),
							response.getEntities(), response.getContext());

		Object isConversationFinished = response.getContext().get("isConversationFinished");

		if (isConversationFinished != null) {
			//return products
			System.out.println(response.getOutput().getText().get(0));
			//conversation is finished
			conversation.setConversationFinished((boolean)isConversationFinished);
			conversationService.saveConversation(conversation);
			return prepareViewMessage(response.getOutput().getText().get(0), getProducts(prepareKeywords(conversation.getEntities())), response.getContext().getConversationId());
		}
		else {
			System.out.println(response.getOutput().getText().get(0));
			conversationService.saveConversation(conversation);
			return prepareViewMessage(response.getOutput().getText().get(0), null, response.getContext().getConversationId());
		}
	}

	private Conversation updateConversation(Conversation conversation, String conversationId, String message, List<RuntimeEntity> entities, Context context) {
		conversation.setConversationId(conversationId);
		conversation.setContext(context);
		conversation.setMessages(conversationService.addMessage(conversation,message));
		for (RuntimeEntity entity : entities) {
			conversation.setEntities(conversationService.addEntity(conversation, entity.getValue()));
		}
		return conversation;
	}

	public void saveRating(Rating rating) {
		Conversation conversation = conversationService.findByConversationId(rating.getConversationId());
		conversationService.updateConversationWithRating(rating, conversation);
	}
}
