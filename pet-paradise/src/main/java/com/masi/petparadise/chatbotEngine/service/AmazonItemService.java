package com.masi.petparadise.chatbotEngine.service;

import am.ik.aws.apa.AwsApaRequester;
import am.ik.aws.apa.AwsApaRequesterImpl;
import am.ik.aws.apa.jaxws.Item;
import am.ik.aws.apa.jaxws.ItemSearchRequest;
import am.ik.aws.apa.jaxws.ItemSearchResponse;
import am.ik.aws.apa.jaxws.Items;
import com.masi.petparadise.chatbotEngine.model.PetSupplyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
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

    public List<PetSupplyItem> SearchItemByKeywords(String keywords) {
        setRequestParams(keywords);
        ItemSearchResponse response = requester.itemSearch(request);
        String moreResultsURL = response.getItems().get(0).getMoreSearchResultsUrl();
        List<PetSupplyItem> items = new ArrayList<>();
        List<Item> itemsFromResponse = response.getItems().get(0).getItem();
        for(Item item : itemsFromResponse) {
            items.add(createPetSupplyItem(item, moreResultsURL));
        }

        return items;
    }

    private PetSupplyItem createPetSupplyItem(Item item, String moreResultsUrl) {
        PetSupplyItem petSupplyItem = new PetSupplyItem();
        petSupplyItem.setImage(item.getLargeImage());
        petSupplyItem.setItemAttributes(item.getItemAttributes());
        petSupplyItem.setDetailedPageURL(item.getDetailPageURL());
        petSupplyItem.setMoreOffersURL(moreResultsUrl);
        return petSupplyItem;
    }

    private void setRequestParams(String keywords) {
        request.setSearchIndex("PetSupplies");
        request.setKeywords(keywords);
        request.getResponseGroup().add("Images");
        request.getResponseGroup().add("ItemAttributes");
    }

    public int numberOfProducts(String keywords) {
        setRequestParams(keywords);
        ItemSearchResponse response = requester.itemSearch(request);
        Items items = response.getItems().get(0);
        return items.getTotalResults().intValue();
    }

}
