package com.example.authServer.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthServerController {

    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if ("qusai".equals(username) && "12345".equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}

