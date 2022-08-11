package com.example.intermediate.repository;

import com.example.intermediate.controller.request.PostRequestDto;
import com.example.intermediate.domain.Member;
import com.example.intermediate.domain.Post;
import java.util.List;
import java.util.Optional;

import com.example.intermediate.domain.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByModifiedAtDesc();
  List<Post> findALlByMember(Member member);
  List<Post> findAllByLikes(Long likes);

  List<Post> findAllByMember_Id(Long memberColumn);

}
