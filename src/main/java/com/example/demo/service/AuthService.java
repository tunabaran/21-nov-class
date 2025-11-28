package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.routes.LoginRequest;
import com.example.demo.model.routes.RegisterRequest;
import com.example.demo.model.AuthToken;
import com.example.demo.model.routes.LoginResponse;
import com.example.demo.repository.AuthTokenRepository;
import com.example.demo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public void register(RegisterRequest request){
        User user = userService.findByEmail(request.getEmail());
        if(user != null){
            throw new RuntimeException("User already exists.");
        }

        user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userService.save(user);
    }

    public LoginResponse login(LoginRequest request){
        User user = userService.findByEmail(request.getEmail());

        if(!request.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Wrong pasword.");
        }

        AuthToken authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString());
        authToken.setExpireDate(DateUtils.addHours(new Date(), 3));
        authToken.setUserId(user.getId());

        authTokenRepository.save(authToken);

        LoginResponse response = new LoginResponse();
        response.setUser(user);
        response.setAuth(authToken);

        return response;
    }

    public long checkAndReturnUserId(String token){
        return checkAndReturnAuthToken(token).getUserId();
    }

    public AuthToken checkAndReturnAuthToken(String token){
        AuthToken authToken = authTokenRepository.findByToken(token);
        if(!authToken.getExpireDate().after(new Date())){
            throw new RuntimeException("Expired or invalid token.");
        }
        return authToken;
    }
}
