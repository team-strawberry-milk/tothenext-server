package com.berry.next.activity.storage;

import com.berry.next.common.storage.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "activity_participant")
public class ActivityParticipantEntity extends BaseTimeEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ActivityEntity activity;

    @Column(name = "role", nullable = false, length = 8)
    private String role;
}
