package com.berry.next.activity.application.dto.request;

import com.berry.next.account.domain.Account;
import com.berry.next.activity.domain.ActivityApply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ActivityApplyReq {
    private Long activityId;
    private String title;
    private String detail;

    public ActivityApply to(Account account) {
        return ActivityApply.builder()
                .accountId(account.getId())
                .activityId(activityId)
                .title(title)
                .detail(detail)
                .build();
    }
}
