package com.berry.next.activity.domain;

import org.springframework.stereotype.Service;

@Service
public interface ActivityJoinService {
    ActivityApply apply(ActivityApply apply);
    void withdraw(Long applyId);
}
