package com.berry.next.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthGoogleDto {
    private String email;
    private String picture;
    private String name;
    private String sub;
    private String hd;
}
