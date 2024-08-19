package com.berry.next.activity.application.dto.response;

import com.berry.next.activity.domain.Activity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ActivityDetailRes {

    private final String contents;

    // 참가자를 어떻게 해야 할지 모르겠어서 일단 보류

    public static ActivityDetailRes from(Activity activity) {
        return ActivityDetailRes.builder()
                .contents(activity.getContents())
                .build();
    }
}
