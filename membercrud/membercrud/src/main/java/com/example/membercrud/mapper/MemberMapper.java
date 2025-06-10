package com.example.membercrud.mapper;


import com.example.membercrud.dto.MemberDto;
import com.example.membercrud.entity.Member;
import org.springframework.stereotype.Component;

@Component  // 서버가 시작할때
public class MemberMapper {

    public Member toEntity(MemberDto dto){
        return Member.builder().id(dto.getId()).username(dto.getUsername()).password(dto.getPassword()).email(dto.getEmail()).build();
    }

    public MemberDto toDto(Member member){
        return MemberDto.builder().id(member.getId()).username(member.getUsername()).password(member.getPassword()).email(member.getEmail()).build();
    }


    // toEntity : builder라는 Tool을 통해서 Entity를 DTO로 변환해주는 메서드
    // toDto : builder라는 Tool을 통해서 DTO를 Entity로 변환해주는 메서드
    // Getter 와 Setter를 사용한 방식과 동일

    // Entity : DB에 저장과 관련
}
