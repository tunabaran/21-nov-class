package com.example.demo.controller;

import com.example.demo.model.routes.LoginRequest;
import com.example.demo.model.routes.LoginResponse;
import com.example.demo.model.routes.RegisterRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
    }

    @GetMapping("/check/{token}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void check(@PathVariable String token){
        authService.checkAndReturnAuthToken(token);
    }
}
