package com.example.demo.service;

import com.example.demo.model.Agent;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.routes.AskAdvancedQuestionRequest;
import com.example.demo.model.routes.AskAdvancedQuestionResponse;
import com.example.demo.model.routes.AskSimpleQuestionResponse;
import com.example.demo.model.routes.AskSimpleQuestionRequest;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionsService {

    @Autowired
    private AgentService agentService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionRepository questionRepository;

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

    public AskAdvancedQuestionResponse askAdvanced(AskAdvancedQuestionRequest request){

        Agent agent = agentService.get(request.getAgentId());

        long userId = request.getUserId();
        String definition = agent.getDefinition();
        String message = request.getMessage();

        User user = userService.findById(userId);

        String gptMessage =
                "Aşağıda paylaştığım metinin çerçevesi içerisinde uygun bir cevap ver \n" +
                        definition + "\n soru: " + message;

        String responseMessage = aiAgent.askQuestion(gptMessage);

        Question question = new Question();
        question.setQuestionMessage(message);
        question.setAnswerMessage(responseMessage);
        question.setShortMessage(shortenQuestionWithAnswer(message, responseMessage));
        question.setUser(user);
        question.setTimestamp(new Date());

        questionRepository.save(question);

        AskAdvancedQuestionResponse response = new AskAdvancedQuestionResponse();
//        response.setAgent(agent);
//        response.setResponseMessage(responseMessage);
        return response;
    }

    private String shortenQuestionWithAnswer(String question, String answer){
        String gptMessage =
                "Aşağıda paylaştığım soru ve cevabı tek cümle olacak şekilde daha sonra hatırlayacağın şekilde kısalt. Özet içerisinde soru ile bilgi içersin. 20 kelime sınırı olsun." +
                        "\n soru : " + question + " \n cevap : " + answer;


        return aiAgent.askQuestion(gptMessage);
    }
}
