package com.springboot.service;

import com.springboot.domain.Member;
import com.springboot.domain.MemberFormDto;
import com.springboot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member saveMember(Member member){
        validationDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 데이터 베이스를 조회해서 있으면 가입된 회원
    private void validationDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberId(member.getMemberId());
        if(findMember != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }

    public Member getMemberById(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        System.out.println("2222 " + member.getMemberId());
        return member;
    }

    // 1번 방법
    /*public Member updateMember(Member member){
        return memberRepository.save(member);
    }*/

    // 2번 방법
    public void updateMember(MemberFormDto memberFormDto) {
        Member member = memberRepository.findByMemberId(memberFormDto.getMemberId());

        if(member == null) throw new IllegalStateException("존재하지 않는 회원입니다.");
        member.updateFromDto(memberFormDto,passwordEncoder);
        // memberRepository.save(member);
    }

    

    public void deleteMember(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        memberRepository.deleteById(member.getNum());
    }

    // 인증 회원 정보 가져오기
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println("3333 " + id);
        Member member = memberRepository.findByMemberId(id);
        System.out.println("4444 " + member);
        System.out.println("22222 "+ member);
        if(member == null){
            throw new UsernameNotFoundException(id);
        }

        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();

    }
}
