package com.berry.next.aop.storage;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;

@Aspect
@Component
@RequiredArgsConstructor
public class HibernateFilterAspect {

    private final EntityManager entityManager;

    @Before("execution(* com.berry.next.*.application.*.*(..))")
    public void enableHibernateFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("deletedAccountFilter");
    }
}
