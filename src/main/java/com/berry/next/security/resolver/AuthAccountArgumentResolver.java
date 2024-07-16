package com.berry.next.security.resolver;

import com.berry.next.account.domain.Account;
import com.berry.next.account.storage.AccountJpaRepository;
import com.berry.next.security.domain.AuthAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthAccountArgumentResolver implements HandlerMethodArgumentResolver {

    private final AccountJpaRepository accountRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthAccount.class);
    }

    @Override
    public Account resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthAccount authAccountAnnotation = parameter.getParameterAnnotation(AuthAccount.class);
        assert authAccountAnnotation != null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() == "anonymousUser") {
            return null;
        }

        return accountRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 계정의 접근입니다."))
                .to();
    }
}
