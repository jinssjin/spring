package com.daeguFC.fanPage.controller;

import com.daeguFC.fanPage.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/index")
    public String indexForm(){
        return "/index";
    }
}
