package com.berry.next.activity.domain;

import com.berry.next.account.domain.Account;

import java.util.List;

public interface ActivityService {

    Activity createActivity(Account account, ActivityCreate create);

    Activity modifyActivity(Account account,ActivityModify req);

    void remove(Account account, Activity activity);

    List<Activity> getAllActivities();

    List<Activity> getRecentActivities();

    Activity getActivityById(Long id);
}
