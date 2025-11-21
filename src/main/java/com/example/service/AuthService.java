package com.example.service;

import com.example.model.AuthToken;
import com.example.model.User;
import com.example.model.routes.LoginRequest;
import com.example.model.routes.LoginResponse;
import com.example.model.routes.RegisterRequest;
import com.example.repository.AuthTokenRepository;
import com.example.repository.UserRepository;
import com.example.util.DateUtils;
import org.apache.juli.logging.Log;
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

        if (request.getEmail() == null || request.getEmail().length() < 6 ||
                request.getPassword() == null || request.getEmail().length() < 6 ||
                request.getName() == null || request.getEmail().length() < 3) {

            throw new RuntimeException("Validation error.");
        }

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
        if(request.getEmail() == null || request.getEmail().length() == 0 ||
                request.getPassword() == null || request.getPassword().length() == 0){
            throw new RuntimeException("Email and password cannot be empty.");
        }

        User user = userService.findByEmail(request.getEmail());

        if(!request.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Wrong pasword.");
        }

        AuthToken authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString());
        authToken.setExpireDate(DateUtils.addHours(new Date(), 3));

        authTokenRepository.save(authToken);

        LoginResponse response = new LoginResponse();
        response.setUser(user);
        response.setAuth(authToken);

        return response;
    }
}
