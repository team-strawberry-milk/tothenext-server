package com.berry.next.activity.domain;

import com.berry.next.common.storage.ActivityType;
import com.berry.next.common.storage.Period;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ActivityModify {

    @NotBlank private final Long id;
    @NotBlank private String title;
    @NotBlank private ActivityType type;
    private Integer limit;
    @NotBlank private LocalDate startDate;
    @NotBlank private LocalDate endDate;
    @NotBlank private String contents;
    private String thumbnail;

}
