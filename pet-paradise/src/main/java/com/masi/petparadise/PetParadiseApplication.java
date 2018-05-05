package com.masi.petparadise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;



@SpringBootApplication
public class PetParadiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetParadiseApplication.class, args);
		
		//set version
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);

		//setUsername and you Password inside your Conversation Service (Service Credentials)
		service.setUsernameAndPassword("02bd8aee-854a-44e0-a80d-5cb030ed5302", "17hDwxvu46Xs");
		//send a Message from your code, in this case "Hi"
		MessageRequest newMessage = new MessageRequest.Builder().inputText("Hi").build();
		//set your workspace_id for use the conversation you wants
		MessageResponse response = service.message("a771707b-1ef5-4789-904d-48d15dd01745", newMessage).execute();
		System.out.println(response);
		
	}
}
