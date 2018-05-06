package com.masi.petparadise.watsonCommunication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masi.petparadise.watsonCommunication.service.WatsonCommunication;

@RestController
@RequestMapping(path="/watson")
public class WatsonCommunicationController {

	@Autowired
	WatsonCommunication watsonCommunication;
	
	@GetMapping("/message")
	public ResponseEntity<?> communicate(){
		String responseMessage = watsonCommunication.communicate("hi");
		System.out.println(responseMessage);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
}
