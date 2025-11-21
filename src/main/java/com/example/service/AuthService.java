package com.example.service;

import com.example.model.User;
import com.example.model.routes.LoginRequest;
import com.example.model.routes.RegisterRequest;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterRequest request){

        if (request.getEmail() == null || request.getEmail().length() < 6 ||
                request.getPassword() == null || request.getEmail().length() < 6 ||
                request.getName() == null || request.getEmail().length() < 3) {

            throw new RuntimeException("Validation error.");
        }

        User user = userRepository.findByEmail(request.getEmail());
        if(user != null){
            throw new RuntimeException("User already exists.");
        }

        user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }

    public void login(LoginRequest request){

    }
}
