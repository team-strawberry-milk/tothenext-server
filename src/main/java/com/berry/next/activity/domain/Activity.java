package com.berry.next.activity.domain;

import com.berry.next.account.domain.Account;
import com.berry.next.common.storage.ActivityType;
import com.berry.next.common.storage.Period;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Activity {
    private Long id;
    private String title;
    private final Account host;
    private ActivityType type;
    private Integer limit;
    private LocalDate startDate;
    private LocalDate endDate;
    private String contents;
    private String thumbnail;

    public static Activity from(ActivityCreate create, Account account) {
        return Activity.builder()
                .title(create.getTitle())
                .host(account)
                .type(create.getType())
                .limit(create.getLimit())
                .startDate(create.getStartDate())
                .endDate(create.getEndDate())
                .contents(create.getContents())
                .thumbnail(create.getThumbnail())
                .build();
    }


    public void update(final ActivityModify modify) {
        if (modify.getTitle() != null && !modify.getTitle().isEmpty()) {
            this.title = modify.getTitle();
        }
        if (modify.getType() != null) {
            this.type = modify.getType();
        }
        if (modify.getLimit() != null) {
            this.limit = modify.getLimit();
        }
        /*
        if (modify.getStartDate() != null || modify.getEndDate() != null) {
            this.period = new Period(modify.getStartDate(), modify.getEndDate());
        }

         */
        if (modify.getContents() != null && !modify.getContents().isEmpty()) {
            this.contents = modify.getContents();
        }
        if (modify.getThumbnail() != null && !modify.getThumbnail().isEmpty()) {
            this.thumbnail = modify.getThumbnail();
        }
    }

    @Builder
    public Activity(Long id, String title, Account host, ActivityType type, Integer limit, LocalDate startDate, LocalDate endDate, String contents, String thumbnail) {
        this.id = id;
        this.title = title;
        this.host = host;
        this.type = type;
        this.limit = limit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
        this.thumbnail = thumbnail;
    }
}
