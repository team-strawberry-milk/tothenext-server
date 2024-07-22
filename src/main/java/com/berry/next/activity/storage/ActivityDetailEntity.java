package com.berry.next.activity.storage;

import com.berry.next.activity.domain.ActivityCreate;
import com.berry.next.common.storage.ActivityType;
import com.berry.next.common.storage.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Builder
    public ActivityDetailEntity(ActivityEntity activity, ActivityType type, String title, String detail, Integer limit) {
        this.activity = activity;
        this.title = title;
        this.detail = detail;
    }

    public static ActivityDetailEntity from(ActivityEntity activityEntity, ActivityCreate domain) {
        return ActivityDetailEntity.builder()
                .activity(activityEntity)
                .title(domain.getTitle()) // ActivityCreate에서 title 정보 가져오기
                .detail(domain.getContents()) // ActivityCreate에서 contents 정보를 detail로 가져오기
                .build();
    }

    // detail은 잘 모르겠으니 일단 보류

}