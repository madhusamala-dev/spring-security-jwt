package com.trainingmug.ecommerce.config;

import com.trainingmug.ecommerce.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean

    public SecurityFilterChain
    securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http/*
             Disable CSRF
             */
        .csrf(AbstractHttpConfigurer::disable)
         /*
            Stateless JWT Authentication
         */
        .sessionManagement(session ->
                 session.sessionCreationPolicy(
                 SessionCreationPolicy.STATELESS
                 )
                )
        /*
        Authorization Rules
        */
        .authorizeHttpRequests(auth ->
                               auth

                                /*
                                    Public APIs
                                 */

                                .requestMatchers(
                                        "/api/auth/**"
                                )

                                .permitAll()

                                /*
                                    Product Read
                                 */

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/products/**"
                                )

                                .hasAnyRole(
                                        "CUSTOMER",
                                        "ADMIN"
                                )

                                /*
                                    Product Create
                                 */

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/products/**"
                                )

                                .hasRole("ADMIN")

                                /*
                                    Product Update
                                 */

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/api/products/**"
                                )

                                .hasRole("ADMIN")

                                /*
                                    Product Delete
                                 */

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/api/products/**"
                                )

                                .hasRole("ADMIN")

                                /*
                                    Remaining APIs
                                 */

                                .anyRequest()

                                .authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );


        return http.build();
    }

    @Bean

    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
