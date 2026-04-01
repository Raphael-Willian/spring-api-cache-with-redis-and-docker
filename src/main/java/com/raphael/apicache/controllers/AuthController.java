package com.raphael.apicache.controllers;

import com.raphael.apicache.dtos.request.RegisterUserRequest;
import com.raphael.apicache.dtos.response.RegisterUserResponse;
import com.raphael.apicache.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController (AuthService authService) {
        this.authService = authService;
    }


    @PostMapping
    public ResponseEntity<RegisterUserResponse> createUser(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = authService.create(request);
        return ResponseEntity.ok(response);
    }



}
