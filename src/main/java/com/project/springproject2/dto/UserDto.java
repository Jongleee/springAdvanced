package com.project.springproject2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserDto(){
        this.id=null;
        this.nickname=null;
        this.createdAt=null;
        this.modifiedAt=null;
    }
}
