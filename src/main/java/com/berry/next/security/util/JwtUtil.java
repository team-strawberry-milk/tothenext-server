package com.berry.next.security.util;

import com.berry.next.security.domain.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final String AUTHORITIES_KEY = "role";
    private static final String BEARER_TYPE = "Bearer ";
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = Duration.ofHours(1).toMillis();
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = Duration.ofDays(14).toMillis();
    private final Key key;

    public JwtUtil(@Value("${spring.jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String getTokenString(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_TYPE)) {
            return token.substring(7);
        }

        return null;
    }

    public String generateToken(String subject, Long expirationTime) {
        Long current = (new Date()).getTime();

        String token = Jwts.builder()
                .setSubject(subject)
                .claim(AUTHORITIES_KEY, "ROLE_USER")
                .setExpiration(new Date(current + expirationTime))
                .signWith(key, SignatureAlgorithm.HS512).compact();

        return BEARER_TYPE + token;
    }

    /*
    public Token reissueToken(Authentication authentication, String refreshToken) {
        Long current = (new Date()).getTime();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(current + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256).compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
     */

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).toList();

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long getExpiration(String accessToken) {
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(accessToken)
                .getBody().getExpiration();
        return (expirationDate.getTime() - (new Date()).getTime());
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            throw e;
        }
    }
}
