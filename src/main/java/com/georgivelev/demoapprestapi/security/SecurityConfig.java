package com.georgivelev.demoapprestapi.security;

import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register/student").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register/lector").hasAnyAuthority(UserAuthorities.ADMIN.name(), UserAuthorities.SUPER_ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register/administration").hasAnyAuthority(UserAuthorities.ADMIN.name(), UserAuthorities.SUPER_ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register/admin").hasAnyAuthority(UserAuthorities.SUPER_ADMIN.name())
                        .requestMatchers("/api/v1/auth").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
