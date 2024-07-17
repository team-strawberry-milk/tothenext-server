package com.berry.next.account.domain;

import com.berry.next.account.application.dto.request.GoogleAccountReq;

import javax.net.ssl.SSLSession;

public interface AccountService {
    Account createAccount(AccountCreate create);

    Account createAccountByGoogle(GoogleAccountReq request);

    Account authorize(AccountAuthorize request);

    Account authorizeWithGoogle(GoogleAccountReq request);

    String verifySchool(GoogleAccountReq request);

    Account modify(Account account, AccountModify req);

    void remove(Account account);
}
