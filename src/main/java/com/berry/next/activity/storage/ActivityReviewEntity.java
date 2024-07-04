package com.berry.next.activity.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.common.storage.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "activity_review")
public class ActivityReviewEntity extends BaseTimeEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ActivityEntity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity from;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "detail", nullable = false, columnDefinition = "text")
    private String detail;

    @Builder
    public ActivityReviewEntity(ActivityEntity activity, AccountEntity to, AccountEntity from, String title, String detail) {
        this.activity = activity;
        this.to = to;
        this.from = from;
        this.title = title;
        this.detail = detail;
    }
}
