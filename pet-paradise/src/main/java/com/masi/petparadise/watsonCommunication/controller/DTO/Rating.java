package com.masi.petparadise.watsonCommunication.controller.DTO;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Rating {
	@Id
    private String conversationId;
	int cus;
	int ces;
}
