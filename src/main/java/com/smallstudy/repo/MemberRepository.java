package com.smallstudy.repo;

import com.smallstudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Long countByNickname(String nickname);

}
