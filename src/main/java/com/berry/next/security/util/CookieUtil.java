package com.berry.next.security.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CookieUtil {
    public ResponseCookie generateCookie(String token) throws UnsupportedEncodingException {
        String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);
        return ResponseCookie
                .from("refreshToken", encodedToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/accounts")
                .build();
    }
}
