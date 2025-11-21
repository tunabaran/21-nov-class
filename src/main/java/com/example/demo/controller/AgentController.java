package com.example.demo.controller;

import com.example.demo.model.Agent;
import com.example.demo.model.routes.CreateAgentRequest;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    AgentService agentService;

    @PostMapping("/create")
    public Agent create(@RequestBody CreateAgentRequest request){
        return agentService.create(request);
    }

    @GetMapping("/{id}")
    public Agent findById(@PathVariable long id){
       return agentService.get(id);
    }
}
