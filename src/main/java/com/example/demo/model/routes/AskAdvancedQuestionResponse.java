package com.example.demo.model.routes;

import com.example.demo.model.Agent;

import java.util.List;

public class AskAdvancedQuestionResponse {

    private Agent agent;
    private String responseMessage;

    private List<String> oldMessages;


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<String> getOldMessages() {
        return oldMessages;
    }

    public void setOldMessages(List<String> oldMessages) {
        this.oldMessages = oldMessages;
    }
}
