package com.masi.petparadise.chatbotEngine.controller;

import com.masi.petparadise.chatbotEngine.service.AmazonItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path="/api/amazon")
public class AmazonItemController {

    private final AmazonItemService amazonItemService;

    @GetMapping("/category/{category}")
    public ResponseEntity<?> GetItemsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(amazonItemService.SearchItemByCategory(category), HttpStatus.OK);
    }
}
