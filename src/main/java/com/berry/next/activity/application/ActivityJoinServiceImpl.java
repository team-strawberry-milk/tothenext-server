package com.berry.next.activity.application;

import com.berry.next.activity.domain.ActivityApply;
import com.berry.next.activity.domain.ActivityJoinService;
import com.berry.next.activity.storage.ActivityApplyEntity;
import com.berry.next.activity.storage.ActivityApplyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityJoinServiceImpl implements ActivityJoinService {

    private final ActivityApplyJpaRepository applyRepository;

    @Override
    public ActivityApply apply(ActivityApply apply) {
        return applyRepository.save(ActivityApplyEntity.from(apply)).to();
    }

    @Override
    public void withdraw(Long applyId) {

    }
}
