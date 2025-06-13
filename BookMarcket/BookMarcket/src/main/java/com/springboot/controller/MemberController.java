package com.springboot.controller;

import com.springboot.domain.Member;
import com.springboot.domain.MemberFormDto;
import com.springboot.repository.MemberRepository;
import com.springboot.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/add")
    public String requestAddMemberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/addMember";
    }

    @PostMapping(value = "/add")
    public String submitAddMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/addMember";
        }try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);  // passwordEncoder 패스워드 암호화
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/addMember";
        }
        return "redirect:/members";
    }

    @GetMapping(value = "/update/{memberId}")
    public String requestUpdateMemberForm(@PathVariable(name="memberId") String memberId, Model model){
        Member member = memberService.getMemberById(memberId);
        model.addAttribute("memberFormDto",member);
        return "member/updateMember";
    }

    /*@PostMapping(value = "/update")
    public String submitUpdateMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/updateMember";
        }try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);  // passwordEncoder 패스워드 암호화
            memberService.updateMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/addMember";
        }
        return "redirect:/members";
    }*/

    // 2번 방법

    @PostMapping(value = "/update")
    public String submitUpdateMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/updateMember";
        }try {
            memberService.updateMember(memberFormDto);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/addMember";
        }
        return "redirect:/members";
    }

    @GetMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable(name="memberId") String memberId){
        memberService.deleteMember(memberId);
        return "redirect:/logout";
    }

    @GetMapping
    public String requestMain(){
        return "redirect:/";
    }

}
