package com.berry.next.activity.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityJpaRepository extends JpaRepository<ActivityEntity, Long> {
}
