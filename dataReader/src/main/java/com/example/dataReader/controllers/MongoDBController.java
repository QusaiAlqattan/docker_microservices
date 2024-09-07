package com.example.dataReader.controllers;

import com.example.dataReader.services.AnalysedNumberService;
import com.example.dataReader.model.AnalysedNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
public class MongoDBController {

    @Autowired
    private AnalysedNumberService analysedNumberService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login"; // Return the login page
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        String authUrl = "http://auth-server-container:8081/auth/login";
//        String authUrl = "http://127.0.0.1:8081/auth/login";


        RestTemplate restTemplate = new RestTemplate();

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body
        String requestBody = "{\"username\":\""+ username +"\", \"password\":\""+ password +"\"}";

        // Create the HttpEntity containing the headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the POST request
        ResponseEntity<String> response = restTemplate.postForEntity(authUrl, requestEntity, String.class);

        if (Objects.equals(response.getBody(), "true")) {
            // Redirect to the number input page if authentication is successful
            List<AnalysedNumbers> analysedNumbers = analysedNumberService.getAllAnalysedNumbers();
            model.addAttribute("analysedNumbers", analysedNumbers);
            return "numbersTable";
        } else {
            model.addAttribute("message", "Login Failed");
            return "login"; // Stay on login page if authentication fails
        }
    }
}
