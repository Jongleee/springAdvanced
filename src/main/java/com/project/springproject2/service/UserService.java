package com.project.springproject2.service;

import com.project.springproject2.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    public static boolean validateUser(SignupDto signupDto) {
        return Pattern.matches("^[A-Za-z0-9]{4,12}$", signupDto.getNickname())
                && Pattern.matches("^[A-Za-z0-9]{4,32}$", signupDto.getPassword());
    }
    public static boolean validatePassword(SignupDto signupDto) {
        if(signupDto.getPasswordConfirm().equals(signupDto.getPassword())) return true;
        return false;
    }
}
