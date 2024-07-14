package com.berry.next;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NextApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextApplication.class, args);
	}

}

/*

클라이언트(프론트) -> OAuth : code
클라이언트(프론트) -> OAuth : code -> access_token (유효 시간)
access_token -> 백엔드:
{
    "email": "dev.sunnylee@gmail.com",
    "picture": "https://lh3.googleusercontent.com/a/ACg8ocJT3ZHaemlF-kOh7AkmIUx8MQ7F3AtW9fX5d6BiI8NPKms7ww=s96-c",
    "name": "Sunny LEE",
    "sub": "107846890307463170263",
    "hd": null
}
로그인 혹은 회원가입 성공
 */