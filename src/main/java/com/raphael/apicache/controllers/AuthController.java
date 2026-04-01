package com.raphael.apicache.controllers;

import com.raphael.apicache.dtos.request.LoginUserRequest;
import com.raphael.apicache.dtos.request.RegisterUserRequest;
import com.raphael.apicache.dtos.response.RegisterUserResponse;
import com.raphael.apicache.jwt.JwtService;
import com.raphael.apicache.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthController(AuthService authService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> createUser(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = authService.create(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtService.generateToken(request.getUsername());
    }

}
