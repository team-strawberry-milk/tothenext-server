package com.berry.next.account.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {
    private String email;
    private String password;
    private String campus;
    private String profile;
    private Boolean isCampusCertificated;

    @Builder
    public Account(String email, String password, String campus, String profile, Boolean isCampusCertificated) {
        this.email = email;
        this.password = password;
        this.campus = campus;
        this.profile = profile;
        this.isCampusCertificated = isCampusCertificated;
    }
}
