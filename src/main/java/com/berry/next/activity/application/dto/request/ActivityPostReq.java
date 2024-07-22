package com.berry.next.activity.application.dto.request;

import com.berry.next.account.domain.Account;
import com.berry.next.activity.domain.ActivityCreate;
import com.berry.next.common.storage.ActivityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor // 기본 생성자 추가 (JSON 데이터 바인딩을 위해 필요)
public class ActivityPostReq {
    @NotBlank private String title;
    @NotNull private ActivityType type;
    private Integer limit;
    @NotBlank private LocalDate startDate;
    @NotBlank private LocalDate endDate;
    @NotBlank private String contents;
    private String thumbnail;

    // ActivityCreate 객체로 변환
    public ActivityCreate to(Account host) { // 호스트 값 받아 ActivityCreate 생성
        return ActivityCreate.builder()
                .title(title)
                .type(type)
                .limit(limit)
                .host(host)
                .startDate(startDate)
                .endDate(endDate)
                .contents(contents)
                .thumbnail(thumbnail)
                .build();
    }
}
