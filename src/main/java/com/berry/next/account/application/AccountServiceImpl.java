package com.berry.next.account.application;

import com.berry.next.account.domain.Account;
import com.berry.next.account.domain.AccountCreate;
import com.berry.next.account.domain.AccountService;
import com.berry.next.account.storage.AccountEntity;
import com.berry.next.account.storage.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountJpaRepository accountRepository;

    @Override
    public Account createAccount(AccountCreate create) {
        return accountRepository.save(AccountEntity.from(create)).to();
    }
}
