package com.example.demo.model.routes;

import com.example.demo.model.Agent;

public class AskAdvancedQuestionResponse {

    private Agent agent;
    private String responseMessage;


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
}
