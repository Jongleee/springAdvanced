package com.example.intermediate.repository;

import com.example.intermediate.domain.Comment;
import com.example.intermediate.domain.Member;
import com.example.intermediate.domain.Post;
import java.util.List;

import com.example.intermediate.domain.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByPost(Post post);
  List<Comment> findAllByOrderByModifiedAtDesc();
  List<Comment> findByLike(UserDetailsImpl like);

  List<Comment> findAllByMember(Member member);

}
