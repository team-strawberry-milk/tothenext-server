package com.berry.next.profile.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.common.storage.BaseEntity;
import com.berry.next.common.storage.Period;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "experience")
public class ExperienceEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity account;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "essential", nullable = false, length = 128)
    private String essential;

    @Column(name = "detail", nullable = false, columnDefinition = "text")
    private String detail;

    @Builder
    public ExperienceEntity(AccountEntity account, String title, String essential, String detail) {
        this.account = account;
        this.title = title;
        this.essential = essential;
        this.detail = detail;
    }
}
