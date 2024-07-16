package com.berry.next.security.domain;

import lombok.Builder;
import lombok.Getter;

public record Token(String accessToken, String refreshToken) {
    @Builder
    public Token {}
}
