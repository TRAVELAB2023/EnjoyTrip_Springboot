package com.enjoytrip.common;

import com.enjoytrip.auth.service.JwtService;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.service.MemberService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Component
public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;
    private final JwtService jwtService;

    public CustomArgumentResolver(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, Object> map=jwtService.get();
        Integer memberId=(Integer)map.get("userid");

        return memberId;
    }
}
