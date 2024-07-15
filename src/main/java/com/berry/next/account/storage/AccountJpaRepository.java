package com.berry.next.account.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByEmail(String email);
}
