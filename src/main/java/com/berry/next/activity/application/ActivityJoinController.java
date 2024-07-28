package com.berry.next.activity.application;

import com.berry.next.account.domain.Account;
import com.berry.next.activity.application.dto.request.ActivityApplyReq;
import com.berry.next.activity.domain.ActivityJoinService;
import com.berry.next.security.domain.AuthAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities/join")
public class ActivityJoinController {
    private final ActivityJoinService activityJoinService;

    @PostMapping
    public ResponseEntity<?> join(
            @AuthAccount Account account,
            @RequestBody@Valid final ActivityApplyReq req
    ) {
        activityJoinService.apply(req.to(account));
        return ResponseEntity.ok("활동 신청을 성공하였습니다.");
    }
}
