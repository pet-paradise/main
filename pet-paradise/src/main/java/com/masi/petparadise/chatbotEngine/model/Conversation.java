package com.masi.petparadise.chatbotEngine.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import com.ibm.watson.developer_cloud.assistant.v1.model.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Conversation {

    @Id
    private String conversationId;

    private Context context;

    private List<String> messages;

    private List<String> entities;

    private boolean isConversationFinished;
    
    private int cus;
	private int ces;

    private int CFI;

    private double previousNumberOfProducts;

    private int ACL;

    Map<String, Double> QEI;

    public Conversation() {
        messages = new ArrayList<>();
        entities = new ArrayList<>();
        CFI = 0;
        QEI = new HashMap<>();
        ACL = 4;
    }
}
