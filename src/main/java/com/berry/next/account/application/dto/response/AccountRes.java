package com.berry.next.account.application.dto.response;

import com.berry.next.account.domain.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountRes {
    private final Long id;
    private final String email;
    private final String name;
    private final String campus;
    private final String profile;
    private final Boolean isCampusCertificated;

    public static AccountRes from(Account domain) {
        return AccountRes.builder()
                .id(domain.getId())
                .email(domain.getCampus())
                .name(domain.getName())
                .campus(domain.getCampus())
                .profile(domain.getProfile())
                .isCampusCertificated(domain.getIsCampusCertificated())
                .build();
    }

    @Builder
    public AccountRes(Long id, String email, String name, String campus, String profile, Boolean isCampusCertificated) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.campus = campus;
        this.profile = profile;
        this.isCampusCertificated = isCampusCertificated;
    }
}
