package com.aoop.uber.model.passenger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenBlackList tokenBlacklist;

    public JwtTokenFilter(JwtTokenBlackList tokenBlacklist) {
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null && !tokenBlacklist.isBlacklisted(token)) {
            // Token is valid and not blacklisted
            // Proceed with the request
            filterChain.doFilter(request, response);
        } else {
            // Token is invalid or blacklisted
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String extractToken(HttpServletRequest request) {
        // Get the Authorization header from the request
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Check if the header is null or doesn't start with "Bearer "
        if (header == null || !header.startsWith("Bearer ")) {
            return null; // Invalid token format
        }

        // Extract the token part (remove "Bearer " prefix)
        return header.substring(7);
    }
}
