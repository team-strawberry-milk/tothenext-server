package com.berry.next.activity.storage;

import com.berry.next.common.storage.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "activity_detail")
public class ActivityDetailEntity extends BaseTimeEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ActivityEntity activity;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "detail", nullable = false, columnDefinition = "text")
    private String detail;

    @Builder
    public ActivityDetailEntity(ActivityEntity activity, String title, String detail) {
        this.activity = activity;
        this.title = title;
        this.detail = detail;
    }
}