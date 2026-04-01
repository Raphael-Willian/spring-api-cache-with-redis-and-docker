package com.raphael.apicache.services;


import com.raphael.apicache.dtos.request.RegisterUserRequest;
import com.raphael.apicache.dtos.response.RegisterUserResponse;
import com.raphael.apicache.models.User;
import com.raphael.apicache.repositorys.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        User newUser = new User(request.getUsername(), request.getPassword());

        passwordEncoder.encode(newUser.getPassword());

        userRepository.save(newUser);

        return new RegisterUserResponse("Usuário " + newUser.getUsername() + " criado com sucesso.");

    }

}
