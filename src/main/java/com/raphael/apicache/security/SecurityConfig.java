package com.raphael.apicache.security;

import jakarta.servlet.http.HttpServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();

    } //git push -u origin <nome-da-branch> Executar no proximo push, para que o git entenda que os pushs serão default pra essa branch no github

}
