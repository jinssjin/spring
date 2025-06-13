package com.daeguFC.fanPage.service;

import com.daeguFC.fanPage.dto.MemberDTO;
import com.daeguFC.fanPage.entity.MemberEntity;
import com.daeguFC.fanPage.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.*;

import java.lang.reflect.Member;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public MemberEntity signUpAction(MemberDTO newMemberDTO) {
        MemberEntity newMember = new MemberEntity();
        newMember.setId(newMemberDTO.getId());
        newMember.setPassword(newMemberDTO.getPassword());
        newMember.setName(newMemberDTO.getName());
        newMember.setAge(newMemberDTO.getAge());
        newMember.setEmail(newMemberDTO.getEmail());
        newMember.setPhone(newMemberDTO.getPhone());
        newMember.setAddress(newMemberDTO.getAddress());
        return memberRepository.save(newMember);
    }


    public MemberEntity login(MemberDTO loginUser) {
        return memberRepository.findByName(loginUser.getName()).filter(m -> m.getPassword().equals(loginUser.getPassword())).orElse(null);
    }
}
