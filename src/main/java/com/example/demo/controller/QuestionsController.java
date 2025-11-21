package com.example.demo.controller;


import com.example.demo.model.routes.AskSimpleQuestionRequest;
import com.example.demo.model.routes.AskSimpleQuestionResponse;
import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @PostMapping("/ask-simple")
    public AskSimpleQuestionResponse askSimpleQuestion(@RequestBody AskSimpleQuestionRequest request){
        return questionsService.askSimple(request);
    }
}
