package com.berry.next.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "googleAuthClient", url = "https://www.googleapis.com/oauth2/v3/userinfo")
public interface GoogleAuthClient {
    @GetMapping(headers = "Accept: application/json")
    AuthGoogleDto googleAuthInfo(
      @RequestParam("access_token") String accessToken,
      @RequestParam(value="alt",  defaultValue = "json") String alt
    );
}
