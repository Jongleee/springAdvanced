package com.example.intermediate.repository;

import com.example.intermediate.domain.Comment;
import com.example.intermediate.domain.Member;
import com.example.intermediate.domain.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {

    List<SubComment> findAllByComment(Comment comment);
    List<SubComment> findAllByMember(Member member);
    List<SubComment> findAllByLikes(Long likes);
    List<SubComment> findAllByMember_Id(Long memberColumn);
}
