package com.berry.next.account.domain;

import com.berry.next.account.application.dto.request.GoogleAccountReq;
import com.berry.next.external.AuthGoogleDto;

public interface AccountService {
    Account createAccount(AccountCreate create);

    AuthGoogleDto createAccountByGoogle(GoogleAccountReq request);
}
