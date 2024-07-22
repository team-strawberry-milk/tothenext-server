package com.berry.next.activity.application.dto.response;

import com.berry.next.account.domain.Account;
import com.berry.next.activity.domain.Activity;
import com.berry.next.common.storage.ActivityType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ActivityRes {
    private final Long id;
    private final Account host;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String thumbnail;
    private final ActivityType type;

    public static ActivityRes from(Activity activity) {
        return ActivityRes.builder()
                .id(activity.getId())
                .host(activity.getHost())
                .title(activity.getTitle())
                .startDate(activity.getPeriod().getStartDate())
                .endDate(activity.getPeriod().getEndDate())
                .thumbnail(activity.getThumbnail())
                .type(activity.getType())
                .build();
    }
}
