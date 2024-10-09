package com.smallstudy.service;

import com.smallstudy.config.CustomUser;
import com.smallstudy.domain.Member;
import com.smallstudy.repo.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service("userDetailService")
@RequiredArgsConstructor
public class CustomMemberDetailService implements UserDetailsService     {

    private final MemberRepository memberRepository;


    @PostConstruct
    public void init() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String email = "test@google.com";
        String password = "dwc02207!";
        Member member = new Member(email, encoder.encode(password));
        member.setEmailValid();
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByEmail(username);
        Member member = findMember.orElseThrow(() -> new UsernameNotFoundException(String.format("%s email not found", username)));
        return new CustomUser(member);
    }

}
