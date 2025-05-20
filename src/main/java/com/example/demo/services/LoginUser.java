package com.example.demo.services;

import com.example.demo.classes.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginUser {
    String username;
    String password;

    BCryptPasswordEncoder bp = new BCryptPasswordEncoder();
    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
