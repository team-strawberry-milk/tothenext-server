package com.berry.next.activity.domain;

import com.berry.next.common.storage.ActivityType;
import com.berry.next.account.domain.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ActivityCreate {
    @NotBlank private String title;
    @NotBlank private ActivityType type;
    private Integer limit;
    @NotBlank private Account host;
    @NotBlank private LocalDate startDate;
    @NotBlank private LocalDate endDate;
    @NotBlank private String contents;
    private String thumbnail;

    @Builder
    public ActivityCreate(String title, ActivityType type, Integer limit, Account host, LocalDate startDate, LocalDate endDate, String contents, String thumbnail) {
        this.title = title;
        this.type = type;
        this.limit = limit;
        this.host = host;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
        this.thumbnail = thumbnail;
    }
}
