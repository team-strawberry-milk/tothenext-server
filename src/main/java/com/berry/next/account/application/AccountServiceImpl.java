package com.berry.next.account.application;

import com.berry.next.account.application.dto.request.GoogleAccountReq;
import com.berry.next.account.domain.Account;
import com.berry.next.account.domain.AccountCreate;
import com.berry.next.account.domain.AccountService;
import com.berry.next.account.storage.AccountEntity;
import com.berry.next.account.storage.AccountJpaRepository;
import com.berry.next.external.AuthGoogleDto;
import com.berry.next.external.GoogleAuthClient;
import com.berry.next.external.GoogleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountJpaRepository accountRepository;
    private final GoogleClient googleClient;
    private final GoogleAuthClient googleAuthClient;
    @Value("${spring.security.oauth2.client.registration.google.client-id}") private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}") private String googleClientSecret;

    @Override
    public Account createAccount(AccountCreate create) {
        if (accountRepository.existsByEmail(create.getEmail())) {
            throw new IllegalArgumentException("이미 가입한 이메일입니다.");
        }
        return accountRepository.save(AccountEntity.from(create)).to();
    }

    @Override
    public Account createAccountByGoogle(GoogleAccountReq request) {
        String decode = URLDecoder.decode(request.getToken(), StandardCharsets.UTF_8);
        AuthGoogleDto googleInfo =  googleAuthClient.googleAuthInfo(decode, "json");
        if (accountRepository.existsByEmail(googleInfo.getEmail()))  {
            throw new IllegalArgumentException("이미 가입한 이메일입니다.");
        }
        return accountRepository.save(AccountEntity.builder()
                .email(googleInfo.getEmail())
                .campus(googleInfo.getHd())
                .profile(googleInfo.getPicture())
                .build()).to();
    }
}
