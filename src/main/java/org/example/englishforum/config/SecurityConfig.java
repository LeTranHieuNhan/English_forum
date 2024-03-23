package org.example.englishforum.config;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(
                        request -> request
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/posts/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/posts/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/posts/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/comments/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/comments/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/comments/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/reactions/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/reactions/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/reactions/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "api/v1/categories/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "api/v1/categories/**").authenticated()
                                .anyRequest().permitAll()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}
