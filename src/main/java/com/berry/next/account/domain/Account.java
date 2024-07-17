package com.berry.next.account.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {
    private final Long id;
    private final String email;
    private String name;
    private String password;
    private String campus;
    private String profile;
    private Boolean isCampusCertificated;

    public void certifyCampus(final String campus) {
        this.campus = campus;
        this.isCampusCertificated = Boolean.TRUE;
    }

    public void changeProfile(final AccountModify modify) {
        if (modify.getName() != null && !modify.getName().isEmpty()) {
            this.name = modify.getName();
        }
        if (modify.getProfile() != null && !modify.getProfile().isEmpty()) {
            this.profile = modify.getProfile();
        }
        if (modify.getPassword() != null && !modify.getPassword().isEmpty()) {
            this.password = modify.getPassword();
        }
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
