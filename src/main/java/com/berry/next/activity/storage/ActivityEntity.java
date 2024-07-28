package com.berry.next.activity.storage;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.activity.domain.Activity;
import com.berry.next.activity.domain.ActivityCreate;
import com.berry.next.common.storage.ActivityType;
import com.berry.next.common.storage.Period;
import com.berry.next.common.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "activity")
@SQLDelete(sql = "UPDATE activity SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class ActivityEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host", nullable = false, insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @MapsId
    private AccountEntity host;

    @Column(name = "host", nullable = false)
    private Long hostId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "text")
    private ActivityType type;

    @Column(name = "thumbnail", nullable = false, length = 256)
    private String thumbnail;

    @Column(name = "contents", nullable = false, columnDefinition = "text")
    private String contents;

    @ColumnDefault("1")
    @Column(name = "limit", columnDefinition = "int")
    private Integer limit;

    @Embedded
    private Period period;

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "title", nullable = false, length = 128)
    private String title; // title 필드 추가

    @Builder
    public ActivityEntity(Long id, Long host, ActivityType type, String title, String thumbnail, String contents, Integer limit, LocalDate startDate, LocalDate endDate) {
        this.id= id;
        this.hostId = host;
        this.type = type;
        this.title = title;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.limit = limit;
        this.period = new Period(startDate, endDate);
    }

    public static ActivityEntity from(ActivityCreate create) {
        return ActivityEntity.builder()
                .host(create.getHost().getId())
                .type(create.getType())
                .title(create.getTitle())
                .thumbnail("https://images.tothenext.xyz/profile/profile.png")
                .contents(create.getContents())
                .limit(create.getLimit())
                .startDate(create.getStartDate())
                .endDate(create.getEndDate())
                .build();
    }

    public static ActivityEntity from(Activity activity) {
        return ActivityEntity.builder()
                .host(activity.getHost().getId())
                .title(activity.getTitle())
                .type(activity.getType())
                .thumbnail(activity.getThumbnail())
                .contents(activity.getContents())
                .limit(activity.getLimit())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .host(activity.getHost().getId())
                .id(activity.getId())
                .build();
    }

    // ActivityEntity를 Activity 도메인 객체로 변환
    public Activity to() {
        return Activity.builder()
                .id(id)
                .host(host.to())
                .type(type)
                .title(title)
                .thumbnail(thumbnail)
                .contents(contents)
                .limit(limit)
                .startDate(period.getStartDate())
                .endDate(period.getEndDate())
                .build();
    }
}
