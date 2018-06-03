package com.masi.petparadise.watsonCommunication.controller.DTO;

import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.masi.petparadise.chatbotEngine.model.PetSupplyItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Message {

    private String conversationId;

    private String message;

    private Context context;

    private List<PetSupplyItem> supplyItems;
}
