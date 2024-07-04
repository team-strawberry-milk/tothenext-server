package com.berry.next.profile.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.common.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "language")
public class LanguageEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity account;

    @Column(name = "certificate", nullable = false, length = 36)
    private String certificate;

    @Column(name = "rank", length = 36)
    private String rank;

    @Column(name = "issuer", nullable = false, length = 36)
    private String issuer;

    @Column(name = "acquired_at")
    private LocalDate acquiredAt;

    @Builder
    public LanguageEntity(AccountEntity account, String certificate, String rank, String issuer, LocalDate acquiredAt) {
        this.account = account;
        this.certificate = certificate;
        this.rank = rank;
        this.issuer = issuer;
        this.acquiredAt = acquiredAt;
    }
}
