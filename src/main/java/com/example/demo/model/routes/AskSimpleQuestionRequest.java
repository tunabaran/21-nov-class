package com.example.demo.model.routes;

public class AskSimpleQuestionRequest {

    private long agentId;
    private String message;


    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
