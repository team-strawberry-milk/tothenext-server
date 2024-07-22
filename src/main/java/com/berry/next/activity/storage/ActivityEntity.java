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

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "activity")
public class ActivityEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity host;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "text")
    private ActivityType type;

    @Column(name = "thumbnail", nullable = false, length = 256)
    private String thumbnail;

    @Column(name = "contents", nullable = false, columnDefinition = "text")
    private String contents;

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
    public ActivityEntity(AccountEntity host, ActivityType type, String title, String thumbnail, String contents, Integer limit, LocalDate startDate, LocalDate endDate) {
        this.host = host;
        this.type = type;
        this.title = title;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.limit = limit;
        this.period = new Period(startDate, endDate);
    }
}
