package com.example.intermediate.service;

import com.example.intermediate.controller.request.LikeReadRequestDto;
import com.example.intermediate.controller.response.*;
import com.example.intermediate.domain.Comment;
import com.example.intermediate.domain.Member;
import com.example.intermediate.domain.Post;
import com.example.intermediate.domain.SubComment;
import com.example.intermediate.repository.CommentRepository;
import com.example.intermediate.repository.MemberRepository;
import com.example.intermediate.repository.PostRepository;
import com.example.intermediate.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeReadService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;

    @Transactional
    public ResponseDto<?> showLike(LikeReadRequestDto likeReadRequestDto) {

        String nickname = likeReadRequestDto.getNickname();

        if (!memberRepository.existsByNickname(nickname)) {
            return ResponseDto.fail("Not_Found", "아이디가 없습니다.");
        } else if (nickname.equals("")) {
            return ResponseDto.fail("Not_Found", "아이디를 입력해주세요.");
        }

        Optional<Member> member = memberRepository.findByNickname(nickname);
        long memberColumn = member.get().getId();

        List<Post> postList = postRepository.findAllByMember_Id(memberColumn);
        List<PostResponseDto> postResponseLikeDto = new ArrayList<>();
        List<PostResponseDto> postResponseUnLikeDto = new ArrayList<>();

        for (Post post : postList) {
            if (post.getLikes() != 0) {
                postResponseLikeDto.add(
                        PostResponseDto.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .content(post.getContent())
                                .imgUrl(post.getImgUrl())
                                .author(post.getMember().getNickname())
                                .likes(post.getLikes())
                                .createdAt(post.getCreatedAt())
                                .modifiedAt(post.getModifiedAt())
                                .build()
                );
            } else {
                postResponseUnLikeDto.add(
                        PostResponseDto.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .content(post.getContent())
                                .imgUrl(post.getImgUrl())
                                .author(post.getMember().getNickname())
                                .likes(post.getLikes())
                                .createdAt(post.getCreatedAt())
                                .modifiedAt(post.getModifiedAt())
                                .build()
                );
            }
        }

        List<Comment> commentList = commentRepository.findAllByMember_Id(memberColumn);
        List<CommentResponseDto> commentResponseLikeDto = new ArrayList<>();
        List<CommentResponseDto> commentResponseUnLikeDto = new ArrayList<>();

        for (Comment comment : commentList) {
            if (comment.getLikes() != 0) {
                commentResponseLikeDto.add(
                        CommentResponseDto.builder()
                                .id(comment.getId())
                                .content(comment.getContent())
                                .author(comment.getMember().getNickname())
                                .likes(comment.getLikes())
                                .createdAt(comment.getCreatedAt())
                                .modifiedAt(comment.getModifiedAt())
                                .build()
                );
            } else {
                commentResponseUnLikeDto.add(
                        CommentResponseDto.builder()
                                .id(comment.getId())
                                .content(comment.getContent())
                                .author(comment.getMember().getNickname())
                                .likes(comment.getLikes())
                                .createdAt(comment.getCreatedAt())
                                .modifiedAt(comment.getModifiedAt())
                                .build()
                );
            }
        }
        List<SubComment> subCommentList = subCommentRepository.findAllByMember_Id(memberColumn);
        List<SubCommentResponseDto> SubCommentResponseLikeDto = new ArrayList<>();
        List<SubCommentResponseDto> SubCommentResponseUnLikeDto = new ArrayList<>();

        for (SubComment subComment : subCommentList) {
            if (subComment.getLikes() != 0) {
                SubCommentResponseLikeDto.add(
                        SubCommentResponseDto.builder()
                                .id(subComment.getId())
                                .content(subComment.getContent())
                                .author(subComment.getMember().getNickname())
                                .likes(subComment.getLikes())
                                .createdAt(subComment.getCreatedAt())
                                .modifiedAt(subComment.getModifiedAt())
                                .build()
                );
            } else {
                SubCommentResponseUnLikeDto.add(
                        SubCommentResponseDto.builder()
                                .id(subComment.getId())
                                .content(subComment.getContent())
                                .author(subComment.getMember().getNickname())
                                .likes(subComment.getLikes())
                                .createdAt(subComment.getCreatedAt())
                                .modifiedAt(subComment.getModifiedAt())
                                .build()
                );
            }
        }
        return ResponseDto.success(
                LikeReadResponseDto.builder()
                        .postResponseLikeDto(postResponseLikeDto)
                        .postResponseUnLikeDto(postResponseUnLikeDto)
                        .commentResponseLikeDto(commentResponseLikeDto)
                        .commentResponseUnLikeDto(commentResponseUnLikeDto)
                        .SubCommentResponseLikeDto(SubCommentResponseLikeDto)
                        .SubCommentResponseUnLikeDto(SubCommentResponseUnLikeDto)
                        .build()
        );
    }
}
