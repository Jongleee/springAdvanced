package com.project.springproject2.dto;

import com.project.springproject2.model.Post;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
    private Long postId;
    private String author;
    private String content;
    private Post post;
}