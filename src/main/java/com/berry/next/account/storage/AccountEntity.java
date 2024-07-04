package com.berry.next.account.storage;

import com.berry.next.common.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity extends BaseEntity {
    @Column(name = "email", nullable = false, length = 96)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "campus", nullable = true, length = 255)
    private String campus;

    @Column(name = "is_campus_certificated", nullable = false)
    private Boolean isCampusCertificated;

    @Builder
    public AccountEntity(String email, String password, String campus, Boolean isCampusCertificated) {
        this.email = email;
        this.password = password;
        this.campus = campus;
        this.isCampusCertificated = isCampusCertificated;
    }
}
