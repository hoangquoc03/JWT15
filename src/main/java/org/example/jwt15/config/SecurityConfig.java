package org.example.jwt15.config;


import lombok.RequiredArgsConstructor;
import org.example.jwt15.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Auth
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        // Product
                        .requestMatchers(HttpMethod.GET,
                                "/api/products/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/products/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/products/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/products/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        // Order
                        .requestMatchers(HttpMethod.POST,
                                "/api/orders")
                        .hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.GET,
                                "/api/orders/my")
                        .hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.GET,
                                "/api/orders")
                        .hasAnyRole("STAFF", "ADMIN")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/orders/*/status")
                        .hasRole("STAFF")

                        // User
                        .requestMatchers(HttpMethod.GET,
                                "/api/users/me")
                        .authenticated()

                        .requestMatchers(HttpMethod.PUT,
                                "/api/users/**")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/reviews")
                        .hasRole("CUSTOMER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/products/*/reviews")
                        .authenticated()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/reports/revenue")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}