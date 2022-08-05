package com.project.springproject2.model;

import java.time.LocalDateTime;

public interface CommentMapping {
    Long getId();
    String getTitle();
    String getContent();
    Comment getCommentResponseDtoList();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();

}
