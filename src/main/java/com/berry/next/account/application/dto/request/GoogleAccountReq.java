package com.berry.next.account.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GoogleAccountReq {
    @NotBlank private String token;
}
