package com.project.springproject2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    private String nickname;
    private String password;
    private String passwordConfirm;
}
