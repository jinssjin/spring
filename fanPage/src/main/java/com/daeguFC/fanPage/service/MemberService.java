package com.daeguFC.fanPage.service;

import com.daeguFC.fanPage.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.*;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;
}
