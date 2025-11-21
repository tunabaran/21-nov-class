package com.example.demo.service;

import com.example.demo.model.Agent;
import com.example.demo.model.routes.CreateAgentRequest;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Agent get(long id){
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        if(optionalAgent.get() == null){
            throw new RuntimeException("Agent not found.");
        }
        return optionalAgent.get();
    }
}
