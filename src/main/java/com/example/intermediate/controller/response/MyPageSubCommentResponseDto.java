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
public class MyPageSubCommentResponseDto {

    private Long userId;
    private Long subCommentId;
    private String author;
    private String subComment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
