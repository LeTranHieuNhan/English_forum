package org.example.englishforum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf().disable()
//                .authorizeRequests(authorize ->
//                        authorize
//                                .requestMatchers("/v1/greeting").authenticated()
//                                .anyRequest().permitAll()
//                );
//
//        return httpSecurity.build();
//    }
}
