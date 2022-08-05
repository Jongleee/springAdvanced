package com.project.springproject2.controller;

import com.project.springproject2.dto.SignupDto;
import com.project.springproject2.dto.UserDto;
import com.project.springproject2.model.Message;
import com.project.springproject2.model.User;
import com.project.springproject2.repository.UserRepository;
import com.project.springproject2.security.provider.JwtTokenProvider;
import com.project.springproject2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원 가입 요청 처리
    @PostMapping("/api/member/signup")
//    public Long registerUser(@RequestBody Map<String, String> user) {
    public ResponseEntity<Message> registerUser(@RequestBody SignupDto signupDto) {
        if(userRepository.findByNickname(signupDto.getNickname()).isPresent()){
            Message message = new Message();
            message.setData("중복된 닉네임입니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        if(!UserService.validatePassword(signupDto)){
            Message message = new Message();
            message.setData("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);

        }
        else if (UserService.validateUser(signupDto)) {
            userRepository.save(User.builder()
                    .nickname(signupDto.getNickname())
                    .password(passwordEncoder.encode(signupDto.getPassword()))
                    .build()).getId();
            User member = userRepository.findByNickname(signupDto.getNickname())
                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
            Message message = new Message();
            UserDto userDto=new UserDto();
            userDto.setId(member.getId());
            userDto.setNickname(member.getNickname());
            userDto.setCreatedAt(member.getCreatedAt());
            userDto.setModifiedAt(member.getModifiedAt());
            message.setData(userDto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 로그인
//    @PostMapping("/api/member/login")
//    public String login(@RequestBody Map<String, String> user) {
//        User member = userRepository.findByNickname(user.get("nickname"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
//        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//    }
    @PostMapping("/api/member/login")
    public ResponseEntity<Message> login(@RequestBody Map<String, String> user) {
        if(userRepository.findByNickname(user.get("nickname")).isEmpty()){
            Message message = new Message();
            message.setData("사용자를 찾을 수 없습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        User member = userRepository.findByNickname(user.get("nickname"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            Message message = new Message();
            message.setData("사용자를 찾을 수 없습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        String token=jwtTokenProvider.createToken(member.getUsername());
        String token1=jwtTokenProvider.createRefreshToken(member.getUsername());
        Message message = new Message();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Token",token);
        responseHeaders.add("Refresh-Token",token1);
        UserDto userDto=new UserDto();
        userDto.setId(member.getId());
        userDto.setNickname(member.getNickname());
        userDto.setCreatedAt(member.getCreatedAt());
        userDto.setModifiedAt(member.getModifiedAt());
        message.setData(userDto);
        return new ResponseEntity<>(message, responseHeaders, HttpStatus.CREATED);
    }

}