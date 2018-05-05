package com.masi.petparadise.chatbotEngine.service;

import am.ik.aws.apa.AwsApaRequester;
import am.ik.aws.apa.AwsApaRequesterImpl;
import am.ik.aws.apa.jaxws.Item;
import am.ik.aws.apa.jaxws.ItemSearchRequest;
import am.ik.aws.apa.jaxws.ItemSearchResponse;
import am.ik.aws.apa.jaxws.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonItemService {

    private final AwsApaRequester requester;
    private final ItemSearchRequest request;

    @Autowired
    public AmazonItemService() {
        this.requester = new AwsApaRequesterImpl();
        this.request = new ItemSearchRequest();
    }

    public List<Item> SearchItemByCategory(String category) {
        request.setSearchIndex(category);
        request.setKeywords("");
        ItemSearchResponse response = requester.itemSearch(request);
        //List<Item> list = response.getItems().get(0).getItem();
        return response.getItems().get(0).getItem();
    }

}
