package com.berry.next.security.service;

import com.berry.next.redis.RedisUtil;
import com.berry.next.security.domain.Token;
import com.berry.next.security.util.CookieUtil;
import com.berry.next.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = Duration.ofHours(1).toMillis();
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = Duration.ofDays(14).toMillis();

    public Token issue(Long id) {
        String refreshToken = jwtUtil.generateToken(String.valueOf(id), REFRESH_TOKEN_EXPIRE_TIME);
        redisUtil.setValues(jwtUtil.getTokenString(refreshToken), String.valueOf(id), REFRESH_TOKEN_EXPIRE_TIME);

        return Token.builder()
                .accessToken(jwtUtil.generateToken(String.valueOf(id), ACCESS_TOKEN_EXPIRE_TIME))
                .refreshToken(refreshToken)
                .build();
    }


    public Token reissue(String bearerToken) {
        String token = jwtUtil.getTokenString(bearerToken);
        String id = redisUtil.getValues(token);
        return Token.builder()
                .accessToken(jwtUtil.generateToken(id, ACCESS_TOKEN_EXPIRE_TIME))
                .build();
    }

    public String generateCookie(String token) throws UnsupportedEncodingException {
        return cookieUtil.generateCookie(token).toString();
    }
}
