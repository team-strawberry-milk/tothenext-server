package com.berry.next.security.service;

import com.berry.next.security.domain.Token;
import com.berry.next.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = Duration.ofHours(1).toMillis();
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = Duration.ofDays(14).toMillis();

    public Token issue(Long id) {
        return Token.builder()
                .accessToken(jwtUtil.generateToken(String.valueOf(id), ACCESS_TOKEN_EXPIRE_TIME))
                .refreshToken(jwtUtil.generateToken(String.valueOf(id), REFRESH_TOKEN_EXPIRE_TIME))
                .build();
    }
}
