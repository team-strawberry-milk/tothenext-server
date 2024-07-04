package com.berry.next.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {
    @GetMapping
    public String hello() {
        return "Hello, To The Next Server / team strawberry-milk";
    }
}
