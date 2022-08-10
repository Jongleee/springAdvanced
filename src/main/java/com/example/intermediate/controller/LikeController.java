package com.example.intermediate.controller;


import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


    @RequestMapping(value = "/api/auth/like/post/{postId}", method = RequestMethod.POST)
    public ResponseDto<?> likePost(@PathVariable Long postId, HttpServletRequest request) {
        return likeService.likePost(postId, request);
    }

    @RequestMapping(value = "/api/auth/like/comment/{commentId}", method = RequestMethod.POST)
    public ResponseDto<?> likeComment(@PathVariable Long commentId, HttpServletRequest request) {
        return likeService.likeComment(commentId, request);
    }

    @RequestMapping(value = "/api/auth/like/subcomment/{subCommentId}", method = RequestMethod.POST)
    public ResponseDto<?> likeSubComment(@PathVariable Long subCommentId, HttpServletRequest request) {
        return likeService.likeSubComment(subCommentId, request);
    }


    @RequestMapping(value = "/api/auth/unlike/post/{postId}", method = RequestMethod.DELETE)
    public ResponseDto<?> unlikePost(@PathVariable Long postId, HttpServletRequest request) {
        return likeService.unlikePost(postId, request);
    }

    @RequestMapping(value = "/api/auth/unlike/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseDto<?> unlikeComment(@PathVariable Long commentId, HttpServletRequest request) {
        return likeService.unlikeComment(commentId, request);
    }

    @RequestMapping(value = "/api/auth/unlike/subcomment/{subCommentId}", method = RequestMethod.DELETE)
    public ResponseDto<?> unlikeSubComment(@PathVariable Long subCommentId, HttpServletRequest request) {
        return likeService.unlikeSubComment(subCommentId, request);
    }

}
