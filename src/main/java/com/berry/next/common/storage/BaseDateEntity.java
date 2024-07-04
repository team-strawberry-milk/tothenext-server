package com.berry.next.common.storage;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDateEntity extends BaseEntity {
    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDate startDate;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate endDate;
}
