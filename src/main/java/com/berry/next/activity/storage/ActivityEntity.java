package com.berry.next.activity.storage;


import com.berry.next.account.storage.AccountEntity;
import com.berry.next.common.storage.Period;
import com.berry.next.common.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "activity")
public class ActivityEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AccountEntity host;

    //@Column(name = "title", nullable = false, length = 128)
    //private String title;

    @Column(name = "title", nullable = false, length = 256)
    private String thumbnail;

    @Column(name = "contents", nullable = false, columnDefinition = "text")
    private String contents;

    @Embedded
    private Period period;

    @Builder
    public ActivityEntity(AccountEntity host, String title, String thumbnail, String contents, LocalDate startDate, LocalDate endDate) {
        this.host = host;
        //this.title = title;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.period = new Period(startDate, endDate);
    }
}
