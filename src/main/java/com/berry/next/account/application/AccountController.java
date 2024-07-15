package com.berry.next.account.application;

import com.berry.next.account.application.dto.request.GoogleAccountReq;
import com.berry.next.account.domain.AccountAuthorize;
import com.berry.next.account.domain.AccountCreate;
import com.berry.next.account.domain.AccountService;
import com.berry.next.security.domain.Token;
import com.berry.next.security.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup (
            @Valid @RequestBody final AccountCreate request
    ) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @PostMapping("/signup/google")
    public ResponseEntity<?> signupWithGoogle(
            @RequestBody final GoogleAccountReq request
        ) {
        return ResponseEntity.ok(accountService.createAccountByGoogle(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<Token> signin(
            @RequestBody final AccountAuthorize request
    ) {
        return ResponseEntity.ok(jwtService.issue(accountService.authorize(request).getId()));
    }
}
