package com.springboot.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
// @Builder
public class Member {

/*    public static Member createMember(MemberFormDto dto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(dto.getMemberId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .role(Role.USER)
                .build();
    }*/


    @Id  // 유일키
    @Column(name = "num")  // 컬럼명 지정
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @Column(unique = true)  // memberId는 중복허용 금지
    private String memberId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setMemberId(memberFormDto.getMemberId());
        member.setName(memberFormDto.getName());
        member.setPhone(memberFormDto.getPhone());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }


    public void updateFromDto(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        this.name = memberFormDto.getName();
        this.phone = memberFormDto.getPhone();
        this.email = memberFormDto.getEmail();
        this.address = memberFormDto.getAddress();
        if(memberFormDto.getPassword() != null && !memberFormDto.getPassword().isBlank()){
            this.password = passwordEncoder.encode(memberFormDto.getPassword());
        }
    }
}
