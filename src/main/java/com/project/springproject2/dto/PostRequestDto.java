package com.project.springproject2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {
    private String author;
    private String content;
    private String title;
}

