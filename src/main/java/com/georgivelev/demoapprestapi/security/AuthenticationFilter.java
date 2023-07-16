package com.georgivelev.demoapprestapi.security;

import com.georgivelev.demoapprestapi.dao.repositories.UserRepository;
import com.georgivelev.demoapprestapi.services.JwtService;
import com.georgivelev.demoapprestapi.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.georgivelev.demoapprestapi.appconfig.ApplicationConstants.AUTHORIZATION_HEADER_DEFAULT_NAME;
import static com.georgivelev.demoapprestapi.appconfig.ApplicationConstants.DEFAULT_JJWT_PREFIX;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER_DEFAULT_NAME);

        if(header == null || !header.startsWith(DEFAULT_JJWT_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jjwt = header.substring(DEFAULT_JJWT_PREFIX.length());

        if(jjwt.isBlank() || jjwt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String providedUsername = jwtService.extractUsername(jjwt);

        if(providedUsername == null) {
            log.error("The token provided does not contain any subject{}", jjwt);
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = userService.loadUserByUsername(providedUsername);

        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            if(jwtService.isTokenValid(jjwt, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}
