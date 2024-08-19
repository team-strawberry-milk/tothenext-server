package com.berry.next.activity.domain;

import com.berry.next.account.domain.Account;

public interface ActivityHostService {
    ActivityApplicant findApplicantList(Account host, Long activityId);
    void accept(Long applyId);
    void refuse(Long applyId);
}
