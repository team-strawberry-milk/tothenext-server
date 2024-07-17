package com.berry.next.security.config;

import com.berry.next.security.filter.JwtFilter;
import com.berry.next.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtUtil jwtUtil;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}