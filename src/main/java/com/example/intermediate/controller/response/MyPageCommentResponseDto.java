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
public class MyPageCommentResponseDto {

    private Long userId;
    private Long commentId;
    private String author;
    private Long likes;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
