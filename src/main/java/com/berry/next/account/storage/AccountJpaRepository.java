package com.berry.next.account.storage;

import com.berry.next.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByEmail(String email);

    Optional<AccountEntity> findByEmail(String email);
}
