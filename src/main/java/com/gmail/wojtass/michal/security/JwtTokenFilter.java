package com.gmail.wojtass.michal.security;
//JWT JSON WEB TOKEN
/*
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractJwtFromHeader(request.getHeader("Authorization"));
        if (token != null && JwtTokenUtil.validateToken(token)) {
            SecurityContextHolder.getContext().setAuthentication(JwtTokenUtil.getAuthentication(token));
        }

        filterChain.doFilter(request, response);
    }
    private String extractJwtFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Delete prefix "Bearer "
        }
        return null;
    }
}

 */
