package com.example.demo.model.routes;


import com.example.demo.model.User;
import com.example.demo.model.AuthToken;

public class LoginResponse {

    private User user;

    private AuthToken auth;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthToken getAuth() {
        return auth;
    }

    public void setAuth(AuthToken auth) {
        this.auth = auth;
    }
}
