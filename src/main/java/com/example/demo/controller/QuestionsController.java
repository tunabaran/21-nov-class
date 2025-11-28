package com.example.demo.controller;


import com.example.demo.model.routes.AskAdvancedQuestionRequest;
import com.example.demo.model.routes.AskAdvancedQuestionResponse;
import com.example.demo.model.routes.AskSimpleQuestionRequest;
import com.example.demo.model.routes.AskSimpleQuestionResponse;
import com.example.demo.service.AuthService;
import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private AuthService authService;

    @PostMapping("/ask-simple")
    public AskSimpleQuestionResponse askSimpleQuestion(@RequestBody AskSimpleQuestionRequest request,
                                                       @RequestHeader(name = "xToken") String xToken){

        authService.checkAndReturnAuthToken(xToken);
        return questionsService.askSimple(request);
    }

    @PostMapping("/ask-advanced")
    public AskAdvancedQuestionResponse askAdvanceQuestion(@RequestBody AskAdvancedQuestionRequest request,
                                                          @RequestHeader(name = "xToken") String xToken){
        request.setUserId(authService.checkAndReturnUserId(xToken));

        return questionsService.askAdvanced(request);
    }
}
