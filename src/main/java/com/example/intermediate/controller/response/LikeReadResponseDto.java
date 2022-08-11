package com.example.intermediate.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeReadResponseDto {

    private List<PostResponseDto> postResponseLikeDto;
    private List<PostResponseDto> postResponseUnLikeDto;
    private List<CommentResponseDto> commentResponseLikeDto;
    private List<CommentResponseDto> commentResponseUnLikeDto;
    private List<SubCommentResponseDto> SubCommentResponseLikeDto;
    private List<SubCommentResponseDto> SubCommentResponseUnLikeDto;

}
