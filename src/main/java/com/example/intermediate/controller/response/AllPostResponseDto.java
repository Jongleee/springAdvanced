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
public class AllPostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String imgUrl;
    private int likes;
    private int commentsNum;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}