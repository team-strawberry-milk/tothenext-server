package com.berry.next.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "googleClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleClient {
    @PostMapping(headers = "Content-Length=0")
    AuthGoogleTokenDto getAccessToken (
            @RequestParam("code") String token,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("redirect_uri") String uri,
            @RequestParam("grant_type") String grantType
    );
}
