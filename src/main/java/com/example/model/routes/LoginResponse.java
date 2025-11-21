package com.example.model.routes;


import com.example.model.AuthToken;
import com.example.model.User;

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
