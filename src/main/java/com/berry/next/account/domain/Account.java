package com.berry.next.account.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {
    private final Long id;
    private final String email;
    private final String name;
    private final String password;
    private final String campus;
    private final String profile;
    private final Boolean isCampusCertificated;

    @Builder
    public Account(Long id, String email, String name, String password, String campus, String profile, Boolean isCampusCertificated) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.campus = campus;
        this.profile = profile;
        this.isCampusCertificated = isCampusCertificated;
    }
}
