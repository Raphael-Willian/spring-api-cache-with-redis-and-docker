package com.raphael.apicache.security;

import jakarta.servlet.http.HttpServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable()) //Desativo o csrf pra simplificar o desenvolvimento, visto que a política de segurança será STATELESS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/produto").permitAll() //Rotas que todas as requests estarão permitidas (OBS: mudará para rotas de LOGIN quando a modelagem de dados for feita)
                        .anyRequest().authenticated() // As demais Rotas Exigem Autenticação
                )
                .httpBasic(Customizer.withDefaults())
                .build();

    }

}
