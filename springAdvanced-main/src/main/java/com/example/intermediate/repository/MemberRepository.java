package com.example.intermediate.repository;

import com.example.intermediate.domain.Member;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByNickname(String nickname);
}
