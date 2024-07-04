package com.berry.next.profile.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.common.storage.BaseDateEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "school")
public class SchoolEntity extends BaseDateEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity account;

    @Column(name = "campus", nullable = false, length = 36)
    private String campus;

    @Column(name = "major", nullable = false, length = 36)
    private String major;

    @Column(name = "double_major", length = 36)
    private String doubleMajor;

    @Column(name = "sub_major", length = 36)
    private String subMajor;

    @Column(name = "major_credit", columnDefinition = "mediumint")
    private Long majorCredit;

    @Column(name = "total_credit", columnDefinition = "mediumint")
    private Long totalCredit;

    @Builder
    public SchoolEntity(AccountEntity account, String campus, String major, String doubleMajor, String subMajor, Long majorCredit, Long totalCredit) {
        this.account = account;
        this.campus = campus;
        this.major = major;
        this.doubleMajor = doubleMajor;
        this.subMajor = subMajor;
        this.majorCredit = majorCredit;
        this.totalCredit = totalCredit;
    }
}
