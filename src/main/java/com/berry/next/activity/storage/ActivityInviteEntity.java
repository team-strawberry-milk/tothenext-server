package com.berry.next.activity.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.common.storage.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@Entity(name = "activity_invite")
@SQLDelete(sql = "UPDATE activity_invite SET is_deleted = true WHERE id = ?")
@SQLRestriction("select * from activity_invite where is_deleted = false")
public class ActivityInviteEntity extends BaseTimeEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false, insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @MapsId
    private AccountEntity account;

    @Column(name = "account", nullable = false)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity", nullable = false, insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @MapsId
    private ActivityEntity activity;

    @Column(name = "activity", nullable = false)
    private Long activityId;

    @ColumnDefault("0")
    @Column(name = "is_accepted")
    private Boolean isAccepted;

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
