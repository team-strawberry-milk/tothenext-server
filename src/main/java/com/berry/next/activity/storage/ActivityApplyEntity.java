package com.berry.next.activity.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.activity.domain.ActivityApply;
import com.berry.next.common.storage.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@Entity(name = "activity_apply")
@SQLDelete(sql = "UPDATE activity_apply SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class ActivityApplyEntity extends BaseTimeEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false, insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity account;

    @Column(name = "account", nullable = false)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity", nullable = false, insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ActivityEntity activity;

    @Column(name = "activity", nullable = false)
    private Long activityId;

    @Column(name = "title", length = 512)
    private String title;

    @Column(name = "detail", columnDefinition = "text")
    private String detail;

    @Column(name = "is_accepted")
    private Boolean isAccepted;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder
    public ActivityApplyEntity(Long accountId, Long activityId, String title, String detail, Boolean isAccepted, Boolean isDeleted) {
        this.accountId = accountId;
        this.activityId = activityId;
        this.title = title;
        this.detail = detail;
        this.isAccepted = (isAccepted != null) ? isAccepted : false;
        this.isDeleted = (isDeleted != null) ? isDeleted : false;
    }

    public static ActivityApplyEntity from(ActivityApply domain) {
        return ActivityApplyEntity.builder()
                .accountId(domain.getAccountId())
                .activityId(domain.getActivityId())
                .title(domain.getTitle())
                .detail(domain.getDetail())
                .build();
    }

    public ActivityApply to() {
        return ActivityApply.builder()
                .accountId(accountId)
                .activityId(activityId)
                .title(title)
                .detail(detail)
                .build();
    }
}