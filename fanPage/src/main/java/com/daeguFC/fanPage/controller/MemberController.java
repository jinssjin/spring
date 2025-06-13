package com.daeguFC.fanPage.controller;

import com.daeguFC.fanPage.dto.MemberDTO;
import com.daeguFC.fanPage.entity.MemberEntity;
import com.daeguFC.fanPage.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import lombok.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/index")
    public String indexForm(){ return "index"; }

    @GetMapping("/members/signUp")
    public String signUpForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "/members/signUp";
    }

    @PostMapping("/members/signUp")
    public String signUp(MemberDTO newMember){
        memberService.signUpAction(newMember);
        return "redirect:/index";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "/members/login";
    }

    @PostMapping("/members/login")
    public String loginAction(MemberDTO loginUser, HttpSession session){
        MemberEntity loginMember = memberService.login(loginUser);
        if(loginMember != null){
            session.setAttribute("loginMember", loginMember);
            return "redirect:/index";
        }else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }

}