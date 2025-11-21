package com.example.service;

import com.example.model.Agent;
import com.example.model.routes.CreateAgentRequest;
import com.example.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    public Agent create(CreateAgentRequest request){

        if(request.getType() == null ||request.getType().length() < 3){
            throw new RuntimeException("Type cannot be empty or shorter than 2 letters.");
        }

        if(request.getDefinition() == null || request.getDefinition().split(" ").length < 2){
            throw new RuntimeException("Incorrect definition message.");
        }

        Agent agent = new Agent();
        agent.setType(request.getType());
        agent.setDefinition(request.getDefinition());

        agentRepository.save(agent);

        return agent;
    }
}
