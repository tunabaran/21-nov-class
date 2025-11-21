package com.example.demo.service;

import com.example.demo.model.Agent;
import com.example.demo.model.routes.AskSimpleQuestionResponse;
import com.example.demo.model.routes.AskSimpleQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AIAgent aiAgent;

    public AskSimpleQuestionResponse askSimple(AskSimpleQuestionRequest request){
        Agent agent = agentService.get(request.getAgentId());

        String definition = agent.getDefinition();
        String message = request.getMessage();

        String gptMessage =
                "Aşağıda paylaştığım metinin çerçevesi içerisinde uygun bir cevap ver \n" +
                        definition + "\n soru: " + message;

        String responseMessage = aiAgent.askQuestion(gptMessage);

        AskSimpleQuestionResponse response = new AskSimpleQuestionResponse();
        response.setAgent(agent);
        response.setResponseMessage(responseMessage);
        return response;
    }
}
