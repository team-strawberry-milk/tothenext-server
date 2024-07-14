package com.berry.next.account.storage;

import com.berry.next.account.domain.Account;
import com.berry.next.account.domain.AccountCreate;
import com.berry.next.common.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity extends BaseEntity {
    @Column(name = "email", nullable = false, length = 96)
    private String email;

    @Column(name = "name", nullable = false, length = 96)
    private String name;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "campus", nullable = true, length = 255)
    private String campus;

    @Column(name = "profile", nullable = true, length = 255)
    private String profile;

    @ColumnDefault("0")
    @Column(name = "is_campus_certificated", nullable = false)
    private Boolean isCampusCertificated;

    @PrePersist
    public void prePersist() {
        this.isCampusCertificated = this.isCampusCertificated == null ? Boolean.FALSE : Boolean.TRUE;
    }

    @Builder
    public AccountEntity(String email, String name, String password, String profile, String campus, Boolean isCampusCertificated) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profile = profile;
        this.campus = campus;
        this.isCampusCertificated = isCampusCertificated;
    }

    public static AccountEntity from(AccountCreate domain) {
        return AccountEntity.builder()
                .email(domain.getEmail())
                .name(domain.getName())
                .password(domain.getPassword())
                .profile("https://images.tothenext.xyz/profile/profile.png")
                .build();
    }

    public Account to() {
        return Account.builder()
                .email(email)
                .password(password)
                .profile(profile)
                .campus(campus)
                .isCampusCertificated(isCampusCertificated)
                .build();
    }
}
