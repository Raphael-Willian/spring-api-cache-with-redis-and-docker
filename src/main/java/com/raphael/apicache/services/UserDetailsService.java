package com.raphael.apicache.services;

import com.raphael.apicache.models.CustomUserDetails;
import com.raphael.apicache.models.Users;
import com.raphael.apicache.repositorys.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado"));
        return new CustomUserDetails(user);

    }
}
