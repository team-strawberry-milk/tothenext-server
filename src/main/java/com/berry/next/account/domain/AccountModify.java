package com.berry.next.account.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountModify {
    private final String name;
    private final String profile;
    private String password;

    public void encryptPassword(String password) {
        this.password = password;
    }
}
