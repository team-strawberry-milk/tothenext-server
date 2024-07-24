package com.berry.next.activity.application;

import com.berry.next.account.domain.Account;
import com.berry.next.activity.application.dto.response.ActivityDetailRes;
import com.berry.next.activity.application.dto.response.ActivityRes;
import com.berry.next.activity.domain.ActivityCreate;
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

    @GetMapping()
    public ResponseEntity<List<ActivityRes>> getActivityList() {
        return ResponseEntity.ok(
                activityService.getAllActivities().stream()
                .map(ActivityRes::from)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/home")
    public ResponseEntity<List<ActivityRes>> getActivityRecentList() {
        return ResponseEntity.ok(
                activityService.getRecentActivities().stream()
                .map(ActivityRes::from)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDetailRes> getActivityById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ActivityDetailRes.from(activityService.getActivityById(id))
        );
    }

    @PostMapping()
    public ResponseEntity<ActivityRes> createActivity(
            @AuthAccount Account account,
            @RequestBody @Valid ActivityCreate req
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ActivityRes.from(activityService.createActivity(account, req)));
    }





}
