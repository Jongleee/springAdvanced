package com.project.springproject2.controller;

import com.project.springproject2.model.Message;
import com.project.springproject2.model.Post;
import com.project.springproject2.repository.PostRepository;
import com.project.springproject2.dto.PostRequestDto;
import com.project.springproject2.security.provider.JwtTokenProvider;
import com.project.springproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/auth/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto requestDto ,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        String nickname = jwtTokenProvider.getUserPk(token);
        Post post = new Post(nickname, requestDto);
        postRepository.save(post);
        Message message = new Message();
        message.setData(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/api/post")
    public ResponseEntity<?> getPosts() {
        Message message = new Message();
        List <?> post=postRepository.findAllByOrderByCreatedAtDesc();
        message.setData(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<?> getPosts(@PathVariable Long id) {
        Message message = new Message();
        List <?> post=postRepository.findPostById(id);
        message.setData(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }



    @PutMapping("/api/auth/post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        String nickname = jwtTokenProvider.getUserPk(token);
        if (!postService.checkAuthor(id, nickname)){
            Message message = new Message();
            message.setData("작성자만 삭제할 수 있습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        requestDto.setAuthor(nickname);
        postService.update(id, requestDto);
        Message message = new Message();
        List <?> post=postRepository.findPostById(id);
        message.setData(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/auth/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id ,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        String nickname = jwtTokenProvider.getUserPk(token);
        if (!postService.checkAuthor(id, nickname)){
            Message message = new Message();
            message.setData("작성자만 삭제할 수 있습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        postRepository.deleteById(id);
        Message message = new Message();
        message.setData("success");
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }


}