package com.berry.next.common.storage;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity extends BaseEntity {
    @CreationTimestamp
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP", name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP", name = "modified_at")
    private LocalDateTime modifiedAt;
}