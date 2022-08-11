package com.example.intermediate.service;

import com.example.intermediate.controller.response.*;
import com.example.intermediate.domain.Comment;
import com.example.intermediate.domain.Member;
import com.example.intermediate.domain.Post;
import com.example.intermediate.domain.SubComment;
import com.example.intermediate.jwt.TokenProvider;
import com.example.intermediate.repository.CommentRepository;
import com.example.intermediate.repository.PostRepository;
import com.example.intermediate.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
public class MyPageService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Member validateMember(HttpServletRequest httpServletRequest) {
        if (!tokenProvider.validateToken(httpServletRequest.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

    @Transactional
    public ResponseDto<?> readMyPage(HttpServletRequest httpServletRequest) {

        if (null == httpServletRequest.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == httpServletRequest.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(httpServletRequest);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        List<Post> postList = postRepository.findALlByMember(member);
        List<Comment> commentList = commentRepository.findAllByMember(member);
        List<SubComment> subCommentList = subCommentRepository.findAllByMember(member);

        MyPageResponseDto myPageResponseDto = new MyPageResponseDto();
        List<MyPagePostResponseDto> myPagePostResponseDtoArrayList = new ArrayList<>();
        List<MyPageCommentResponseDto> myPageCommentResponseDtoArrayList = new ArrayList<>();
        List<MyPageSubCommentResponseDto> myPageSubCommentResponseDtoArrayList = new ArrayList<>();

            for (Post post : postList) {
                myPagePostResponseDtoArrayList.add(
                        MyPagePostResponseDto.builder()
                                .userId(post.getMember().getId())
                                .postId(post.getId())
                                .author(post.getMember().getNickname())
                                .likes(post.getLikes())
                                .imgUrl(post.getImgUrl())
                                .title(post.getTitle())
                                .postContent(post.getContent())
                                .createdAt(post.getCreatedAt())
                                .modifiedAt(post.getModifiedAt())
                                .build()
                );
            }

            for (Comment comment : commentList) {
                myPageCommentResponseDtoArrayList.add(
                        MyPageCommentResponseDto.builder()
                                .userId(comment.getMember().getId())
                                .commentId(comment.getId())
                                .author(comment.getMember().getNickname())
                                .likes(comment.getLikes())
                                .comment(comment.getContent())
                                .createdAt(comment.getCreatedAt())
                                .modifiedAt(comment.getModifiedAt())
                                .build()
                );
            }

            for (SubComment subComment : subCommentList) {
                myPageSubCommentResponseDtoArrayList.add(
                        MyPageSubCommentResponseDto.builder()
                                .userId(subComment.getMember().getId())
                                .subCommentId(subComment.getId())
                                .author(subComment.getMember().getNickname())
                                .likes(subComment.getLikes())
                                .subComment(subComment.getContent())
                                .createdAt(subComment.getCreatedAt())
                                .modifiedAt(subComment.getModifiedAt())
                                .build()
                );
            }
        myPageResponseDto.updateResponse(myPagePostResponseDtoArrayList,myPageCommentResponseDtoArrayList,myPageSubCommentResponseDtoArrayList);
        return ResponseDto.success(myPageResponseDto);
    }
}
