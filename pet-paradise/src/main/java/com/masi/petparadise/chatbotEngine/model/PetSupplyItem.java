package com.masi.petparadise.chatbotEngine.model;

import am.ik.aws.apa.jaxws.Image;
import am.ik.aws.apa.jaxws.ItemAttributes;
import lombok.Data;

@Data
public class PetSupplyItem {

    Image image;
    ItemAttributes itemAttributes;
    String detailedPageURL;
    String moreOffersURL;
}
