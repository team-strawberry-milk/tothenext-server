package com.berry.next.activity.domain;

import com.berry.next.account.domain.Account;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ActivityApplicant {
    private final Activity activity;
    private final List<ActivityApply> applicants;

    @Builder
    public ActivityApplicant(Activity activity, List<ActivityApply> applicants) {
        this.activity = activity;
        this.applicants = applicants;
    }
}
