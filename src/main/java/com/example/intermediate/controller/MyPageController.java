package com.example.intermediate.controller;


import com.example.intermediate.controller.request.LikeReadRequestDto;
import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.service.LikeReadService;
import com.example.intermediate.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;
    private final LikeReadService likeReadService;

    @RequestMapping(value = "/api/auth/myPage", method = RequestMethod.GET)
    public ResponseDto<?> validateMembers (HttpServletRequest httpServletRequest) {
        return myPageService.readMyPage(httpServletRequest);
    }

    @GetMapping("/api/likes")
    public ResponseDto<?> showLike(@RequestBody LikeReadRequestDto likeReadDto) {
        return likeReadService.showLike(likeReadDto);
    }
}
