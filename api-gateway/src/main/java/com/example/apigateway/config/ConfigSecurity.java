package com.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class ConfigSecurity{


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity){
    httpSecurity
            .csrf()
            .disable()
       .authorizeExchange(exchange -> exchange
               .pathMatchers("/eureka/**")
                    .permitAll()
               .anyExchange()
               .authenticated())
            .oauth2ResourceServer
                    (ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
    return httpSecurity.build();
    }
}
