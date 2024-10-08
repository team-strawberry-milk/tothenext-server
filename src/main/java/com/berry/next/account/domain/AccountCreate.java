package com.berry.next.account.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountCreate {
    @Email private String email;
    @NotBlank private String name;
    @NotBlank private String password;

    public void setPassword(String s) {
        this.password = s;
    }
}
