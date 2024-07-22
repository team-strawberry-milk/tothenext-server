package com.berry.next.account.storage;

import com.berry.next.account.domain.Account;
import com.berry.next.account.domain.AccountCreate;
import com.berry.next.common.storage.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "account")
@SQLDelete(sql = "UPDATE account SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
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

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.isCampusCertificated = this.isCampusCertificated == null ? Boolean.FALSE : Boolean.TRUE;
        this.isDeleted = this.isDeleted == null ? Boolean.FALSE : Boolean.TRUE;
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

    public void update(Account account) {
        this.password = account.getPassword();
        this.profile = account.getProfile();
        this.name = account.getName();
    }

    public void certifyCampus(Account account) {
        this.campus = account.getCampus();
        this.isCampusCertificated = account.getIsCampusCertificated();
    }

    public void delete() {
        this.isDeleted = Boolean.TRUE;
    }

    public Account to() {
        return Account.builder()
                .id(getId())
                .email(email)
                .name(name)
                .password(password)
                .profile(profile)
                .campus(campus)
                .isCampusCertificated(isCampusCertificated)
                .build();
    }
}
