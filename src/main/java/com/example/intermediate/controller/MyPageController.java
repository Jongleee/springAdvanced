package com.example.intermediate.controller;


import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;

    @RequestMapping(value = "/api/auth/myPage", method = RequestMethod.GET)
    public ResponseDto<?> validateMembers (HttpServletRequest httpServletRequest) {
        return myPageService.readMyPage(httpServletRequest);
    }

//    @GetMapping("/api/likes")
//    public Long showLike() {
//
//    }
}
