package com.project.springproject2.service;

import com.project.springproject2.model.Post;
import com.project.springproject2.repository.PostRepository;
import com.project.springproject2.dto.PostRequestDto;
import com.project.springproject2.repository.UserRepository;
import com.project.springproject2.security.jwt.HeaderTokenExtractor;
import com.project.springproject2.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }
    public boolean checkAuthor(Long id ,String nickname){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        return post.getAuthor().equals(nickname);
    }

}
