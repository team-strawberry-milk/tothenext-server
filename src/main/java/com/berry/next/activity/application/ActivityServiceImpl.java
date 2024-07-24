package com.berry.next.activity.application;

import com.berry.next.account.domain.Account;
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
        Activity activity = activityRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found")).to();
        validateHost(account, activity.getHost());
        activity.update(req);
        return activityRepository.save(ActivityEntity.from(activity)).to();
    }

    @Override
    public void remove(Account account, Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found")).to();
        validateHost(account, activity.getHost());
        activityRepository.deleteById(activity.getId());
    }

    public void validateHost(Account account, Account host) {
        if (!host.equals(account)) {
            throw new AccessDeniedException("해당 대외활동에 대한 권한이 없습니다.");
        }
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
