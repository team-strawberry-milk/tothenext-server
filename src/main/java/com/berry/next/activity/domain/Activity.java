package com.berry.next.activity.domain;

import com.berry.next.account.domain.Account;
import com.berry.next.common.storage.ActivityType;
import com.berry.next.common.storage.Period;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Activity {
    private Long id;
    private String title;
    private final Account host;
    private ActivityType type;
    private Integer limit;
    private Period period;
    private String contents;
    private String thumbnail;


    public void changeActivity(final ActivityModify modify) {
        if (modify.getTitle() != null && !modify.getTitle().isEmpty()) {
            this.title = modify.getTitle();
        }
        if (modify.getType() != null) {
            this.type = modify.getType();
        }
        if (modify.getLimit() != null) {
            this.limit = modify.getLimit();
        }
        if (modify.getStartDate() != null || modify.getEndDate() != null) {
            this.period = new Period(modify.getStartDate(), modify.getEndDate());
        }
        if (modify.getContents() != null && !modify.getContents().isEmpty()) {
            this.contents = modify.getContents();
        }
        if (modify.getThumbnail() != null && !modify.getThumbnail().isEmpty()) {
            this.thumbnail = modify.getThumbnail();
        }
    }

    @Builder
    public Activity(Long id, ActivityType type, Account host, Integer limit, String title, Period period, String contents, String thumbnail) {
        this.id = id;
        this.host = host;
        this.type = type;
        this.limit = limit;
        this.title = title;
        this.period = period;
        this.contents = contents;
        this.thumbnail = thumbnail;
    }
}
