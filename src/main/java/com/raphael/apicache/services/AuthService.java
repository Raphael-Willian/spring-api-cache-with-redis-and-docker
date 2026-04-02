package com.raphael.apicache.services;


import com.raphael.apicache.dtos.request.RegisterUserRequest;
import com.raphael.apicache.dtos.response.RegisterUserResponse;
import com.raphael.apicache.models.Users;
import com.raphael.apicache.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public RegisterUserResponse create(RegisterUserRequest request) {

        Users newUser = new Users(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        userRepository.save(newUser);

        return new RegisterUserResponse("Usuário " + newUser.getUsername() + " criado com sucesso.");

    }

}
