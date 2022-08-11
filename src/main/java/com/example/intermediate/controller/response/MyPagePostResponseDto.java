package com.example.intermediate.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPagePostResponseDto {

    private Long userId;
    private Long postId;
    private String author;
    private Long likes;
    private String title;
    private String imgUrl;
    private String postContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
