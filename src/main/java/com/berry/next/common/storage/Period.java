package com.berry.next.common.storage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor
public class Period {
    @Column(columnDefinition = "date", name = "start_date")
    private LocalDate startDate;

    @Column(columnDefinition = "date", name = "end_date")
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
