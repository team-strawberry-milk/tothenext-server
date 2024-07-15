package com.berry.next.account.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountAuthorize {
    @Email private String email;
    @NotBlank private String password;
}
