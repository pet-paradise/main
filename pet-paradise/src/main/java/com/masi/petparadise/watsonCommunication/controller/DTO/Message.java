package com.masi.petparadise.watsonCommunication.controller.DTO;

import com.masi.petparadise.chatbotEngine.model.PetSupplyItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Message {

    private String message;

    private String conversationId;

    private List<PetSupplyItem> supplyItems;
}
