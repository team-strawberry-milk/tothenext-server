package com.berry.next.account.application;

import com.berry.next.account.application.dto.request.GoogleAccountReq;
import com.berry.next.account.application.dto.response.AccountRes;
import com.berry.next.account.domain.*;
import com.berry.next.security.domain.AuthAccount;
import com.berry.next.security.domain.Token;
import com.berry.next.security.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
    public ResponseEntity<Token> signIn(
            @RequestBody final AccountAuthorize request
    ) throws UnsupportedEncodingException {
        Token token = jwtService.issue(accountService.authorize(request).getId());
        return ResponseEntity.ok()
                .header("Set-Cookie", jwtService.generateCookie(token.refreshToken()))
                .body(token);
    }

    @PostMapping("/signin/google")
    public ResponseEntity<Token> signInWithGoogle(
            @RequestBody final GoogleAccountReq request
    ) {
        return ResponseEntity.ok(jwtService.issue(accountService.authorizeWithGoogle(request).getId()));
    }

    @PostMapping("/reissue")
    public ResponseEntity<Token> reissue(
            @CookieValue(value = "refreshToken") Cookie cookie
    ) {
        return ResponseEntity.ok(jwtService.reissue(cookie.getValue()));
    }

    @PostMapping("/verity/school")
    public ResponseEntity<?> verifySchool(
            @AuthAccount Account account,
            @RequestBody final GoogleAccountReq request
    ) {
        accountService.verifySchool(account, request);
        return ResponseEntity.ok("인증에 성공하였습니다.");
    }

    @GetMapping("/me")
    public ResponseEntity<AccountRes> getAccountInformation(
            @AuthAccount final Account account
    ) {
        return ResponseEntity.ok(AccountRes.from(account));
    }

    @PutMapping("/me")
    public ResponseEntity<AccountRes> modifyAccount(
            @AuthAccount Account account,
            @RequestBody AccountModify req
    ) {
        return ResponseEntity.ok(AccountRes.from(accountService.modify(account, req)));
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> removeAccount(
            @AuthAccount Account account
    ) {
        accountService.remove(account);
        return ResponseEntity.ok("정상적으로 삭제되었습니다.");
    }

}
