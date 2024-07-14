package com.berry.next.account.domain;

import com.berry.next.account.application.dto.request.GoogleAccountReq;

public interface AccountService {
    Account createAccount(AccountCreate create);

    Account createAccountByGoogle(GoogleAccountReq request);
}
