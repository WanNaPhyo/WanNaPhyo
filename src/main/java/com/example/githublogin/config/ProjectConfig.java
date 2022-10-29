package com.example.githublogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    private ClientRegistration clientRegistration(){
        return CommonOAuth2Provider
                .GITHUB
                .getBuilder("github")
                .clientId("7290ff5e02e6b770530c")
                .clientSecret("a28ba60d98119435128605f1d4c3b46fc0c96042")
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        var c=clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.oauth2Login();

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        return http.build();
    }
}
