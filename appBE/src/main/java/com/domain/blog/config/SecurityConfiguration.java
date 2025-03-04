package com.domain.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    String[] whiteList = {
            "/",
            "/blog-api/auth/admin/login",
            "/blog-api/auth/refresh",
            "/blog-api/auth/login-social-media",
            "/blog-api/blog/list",
            "/blog-api/blog/related-list",
            "/blog-api/blog/**",
            "/blog-api/blog/pagination-list",
            "/blog-api/blog/comments/**",
            "/storage/**",
            "/blog-api/auth/account",
            "/blog-api/comment/list",
            "/blog-api/comment/**",
            "/blog-api/category/list",
            "/blog-api/setting",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
    };

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) throws Exception {
        http
                .csrf(c-> c.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(whiteList).permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog-api/setting" ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog-api/blog/**" ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/blog-api/comment/**" ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/blog-api/comment/**" ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(customAuthenticationEntryPoint))

                .formLogin(f-> f.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}





