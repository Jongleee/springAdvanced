package com.example.intermediate.repository;

import com.example.intermediate.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Long countAllByPostId(Long postId);
    Long countAllByCommentId(Long commentId);
    Long countAllBySubCommentId(Long subCommentId);

}
