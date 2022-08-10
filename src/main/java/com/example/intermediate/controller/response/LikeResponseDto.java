package com.example.intermediate.controller.response;

import com.example.intermediate.domain.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto{
    private Long id;
    private String title;
    private String content;
    private String author;
    private List<CommentResponseDto> commentResponseDtoList;
    private Long likes;
}
