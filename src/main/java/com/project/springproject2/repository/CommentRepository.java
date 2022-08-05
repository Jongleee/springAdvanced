package com.project.springproject2.repository;

import com.project.springproject2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPostId(Long id);
    List<Comment> findCommentById(Long id);
}
