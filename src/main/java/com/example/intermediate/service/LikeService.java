package com.example.intermediate.service;

import com.example.intermediate.controller.response.CommentResponseDto;
import com.example.intermediate.controller.response.LikeResponseDto;
import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.controller.response.SubCommentResponseDto;
import com.example.intermediate.domain.*;
import com.example.intermediate.jwt.TokenProvider;
import com.example.intermediate.repository.CommentRepository;
import com.example.intermediate.repository.LikeRepository;
import com.example.intermediate.repository.PostRepository;
import com.example.intermediate.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;
    private final TokenProvider tokenProvider;
    private final PostService postService;
    private final CommentService commentService;
    private final SubCommentService subCommentService;


    @Transactional
    public ResponseDto<?> likePost(Long postId, HttpServletRequest request) {
        Member member = validateMember(request);
        Post post = postService.isPresentPost(postId);
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }
        Like likePost = Like.builder()
                .member(member)
                .post(post)
                .build();
        likeRepository.save(likePost);
        Long likes = likeRepository.countAllByPostId(post.getId());
        post.updateLikes(likes);
        return ResponseDto.success("likeSuccess");
    }
    @Transactional
    public ResponseDto<?> likeComment(Long commentId, HttpServletRequest request) {
        Member member = validateMember(request);
        Comment comment = commentService.isPresentComment(commentId);
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        if (null == comment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }
        Like likeComment = Like.builder()
                .member(comment.getMember())
                .comment(comment)
                .build();
        likeRepository.save(likeComment);
        Long likes = likeRepository.countAllByCommentId(comment.getId());
        comment.updateLikes(likes);
        return ResponseDto.success("likeSuccess");
    }
    @Transactional
    public ResponseDto<?> likeSubComment(Long subCommentId, HttpServletRequest request) {

        Member member = validateMember(request);
        SubComment subComment = subCommentService.isPresentSubComment(subCommentId);

        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        if (null == subComment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 id 입니다.");
        }

        Like likeSubComment = Like.builder()
                .member(subComment.getMember())
                .subComment(subComment)
                .build();

        likeRepository.save(likeSubComment);

        // 해당 대댓글 likes 업데이트
        Long likes = likeRepository.countAllBySubCommentId(subComment.getId());
        subComment.updateLikes(likes);
        return ResponseDto.success("likeSuccess");
    }

    @Transactional
    public ResponseDto<?> unlikePost(Long postId, HttpServletRequest request) {
        Member member = validateMember(request);
        Post post = postService.isPresentPost(postId);
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }
        // 해당 게시물의 like 수
        Long likesTotal = likeRepository.countAllByPostId(post.getId());
        // like 삭제
        if (likesTotal != 0) {
            likeRepository.deleteById(likesTotal);
            // like 업데이트
        }
        Long likes = likeRepository.countAllByPostId(post.getId());
        post.updateLikes(likes);
        return ResponseDto.success("UnlikeSuccess");
    }

    @Transactional
    public ResponseDto<?> unlikeComment(Long commentId, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Comment comment = commentService.isPresentComment(commentId);
        if (null == comment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 id 입니다.");
        }

        // 해당 댓글의 like 수
        Long likesTotal = likeRepository.countAllByCommentId(comment.getId());
        if (likesTotal != 0) {
            // like 삭제
            likeRepository.deleteById(likesTotal);


        }
        Long likes = likeRepository.countAllByCommentId(comment.getId());
        comment.updateLikes(likes);
        // like 업데이트
        return ResponseDto.success("UnlikeSuccess");
    }

    @Transactional
    public ResponseDto<?> unlikeSubComment(Long subCommentId, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        SubComment subComment = subCommentService.isPresentSubComment(subCommentId);
        if (null == subComment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 id 입니다.");
        }

        // 해당 대댓글의 like 수
        Long likesTotal = likeRepository.countAllBySubCommentId(subComment.getId());
        if (likesTotal != 0) {
            // like 삭제
            likeRepository.deleteById(likesTotal);
            // like 업데이트
            Long likes = likeRepository.countAllBySubCommentId(subComment.getId());
            subComment.updateLikes(likes);
        }
       return ResponseDto.success("UnlikeSuccess");
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(
                request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }


}
