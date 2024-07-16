package com.berry.next.account.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {
    private final Long id;
    private final String email;
    private final String name;
    private final String password;
    private String campus;
    private String profile;
    private Boolean isCampusCertificated;

    void certifyCampus(String campus) {
        this.campus = campus;
        this.isCampusCertificated = Boolean.TRUE;
    }

    void changeProfile(String profile) {
        this.profile = profile;
    }

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
