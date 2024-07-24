package com.berry.next.activity.application.dto.response;

import com.berry.next.account.application.dto.response.AccountRes;
import com.berry.next.account.domain.Account;
import com.berry.next.activity.domain.Activity;
import com.berry.next.common.storage.ActivityType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ActivityRes {
    private final Long id;
    private final AccountRes host;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String thumbnail;
    private final ActivityType type;

    @Builder
    public ActivityRes(Long id, AccountRes host, String title, LocalDate startDate, LocalDate endDate, String thumbnail, ActivityType type) {
        this.id = id;
        this.host = host;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.thumbnail = thumbnail;
        this.type = type;
    }

    public static ActivityRes from(Activity activity) {
        return ActivityRes.builder()
                .id(activity.getId())
                .host(AccountRes.from(activity.getHost()))
                .title(activity.getTitle())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .thumbnail(activity.getThumbnail())
                .type(activity.getType())
                .build();
    }
}
