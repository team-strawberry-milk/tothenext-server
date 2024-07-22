package com.berry.next.activity.application;

import com.berry.next.account.domain.Account;
import com.berry.next.activity.application.dto.request.ActivityPostReq;
import com.berry.next.activity.application.dto.response.ActivityDetailRes;
import com.berry.next.activity.application.dto.response.ActivityRes;
import com.berry.next.activity.domain.Activity;
import com.berry.next.activity.domain.ActivityService;
import com.berry.next.security.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.berry.next.security.domain.AuthAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final JwtService jwtService;

    @GetMapping("")
    public ResponseEntity<List<ActivityRes>> getActivityList() {
        List<Activity> activities = activityService.getAllActivities();

        List<ActivityRes> activityResList = activities.stream()
                .map(ActivityRes::from) // Activity를 ActivityRes로 변환
                .collect(Collectors.toList());

        return ResponseEntity.ok(activityResList);
    }

    @GetMapping("/home")
    public ResponseEntity<List<ActivityRes>> getActivityRecentList() {
        List<Activity> activities = activityService.getRecentActivities();

        List<ActivityRes> activityResList = activities.stream()
                .map(ActivityRes::from) // Activity를 ActivityRes로 변환
                .collect(Collectors.toList());

        return ResponseEntity.ok(activityResList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDetailRes> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id); // ID로 활동 조회
        return ResponseEntity.ok(ActivityDetailRes.from(activity));
    }

    @PostMapping("")
    public ResponseEntity<ActivityRes> createActivity(
            @AuthAccount Account account, // 인증된 사용자 정보 가져오기
            @RequestBody @Valid ActivityPostReq req
    ) {
        Activity activity = activityService.createActivity(req.to(account)); // ActivityCreate 객체 생성 및 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(ActivityRes.from(activity));
    }



}
