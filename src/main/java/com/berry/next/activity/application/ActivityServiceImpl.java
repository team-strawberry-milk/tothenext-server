package com.berry.next.activity.application;

import com.berry.next.account.domain.Account;
import com.berry.next.account.storage.AccountEntity;
import com.berry.next.account.storage.AccountJpaRepository;
import com.berry.next.activity.domain.Activity;
import com.berry.next.activity.domain.ActivityCreate;
import com.berry.next.activity.domain.ActivityModify;
import com.berry.next.activity.domain.ActivityService;
import com.berry.next.activity.storage.ActivityDetailJpaRepository;
import com.berry.next.activity.storage.ActivityEntity;
import com.berry.next.activity.storage.ActivityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityJpaRepository activityRepository;
    private final ActivityDetailJpaRepository activityDetailRepository;
    private final AccountJpaRepository accountRepository;

    @Override
    public Activity createActivity(Account account, ActivityCreate create) {
        Activity activity = Activity.from(create, account);
        return activityRepository.save(ActivityEntity.from(activity)).to();
    }

    @Override
    public Activity modifyActivity(Account account, ActivityModify req) {
        ActivityEntity activityEntity = activityRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found"));

        if (!activityEntity.getHost().getId().equals(account.getId())) {
            throw new AccessDeniedException("해당 대외활동을 수정할 권한이 없습니다.");
        }

        Activity activity = activityEntity.to();
        activity.changeActivity(req);

        AccountEntity hostEntity = accountRepository.findById(activity.getHost().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 수정된 Activity 도메인 객체를 fromDomain 메서드를 사용하여 ActivityEntity로 변환
        activityEntity = ActivityEntity.fromDomain(activity, hostEntity);
        activityRepository.save(activityEntity);

        return activityEntity.to();
    }


    @Override
    public void remove(Account account, Activity activity) {

        if (!activity.getHost().equals(account)) {
            throw new AccessDeniedException("해당 대외활동을 삭제할 권한이 없습니다.");
        }

        activityRepository.findById(activity.getId())
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 접근입니다."))
                .delete();
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll()
                .stream()
                .map(ActivityEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> getRecentActivities() {
        return activityRepository.findAll(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream()
                .map(ActivityEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
                .map(ActivityEntity::to)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id값과 일치하는 대외활동이 존재하지 않습니다."));
    }
}
