package com.berry.next.activity.domain;

import com.berry.next.account.domain.Account;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ActivityApply {
    private final Long applyId;
    private final Long accountId;
    private final Long activityId;
    private final String title;
    private final String detail;

    @Builder
    public ActivityApply(Long applyId, Long accountId, Long activityId, String title, String detail) {
        this.applyId = applyId;
        this.accountId = accountId;
        this.activityId = activityId;
        this.title = title;
        this.detail = detail;
    }
}
