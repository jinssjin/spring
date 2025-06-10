package Membership.memberInfo.controller;


import Membership.memberInfo.domain.Member;
import Membership.memberInfo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/new")  // 사이트 접속할 주소 http://localhost:8080/new  // GetMapping은 불러올 때
    public String form(Model model){
        model.addAttribute("member",new Member());
        return "members/form";  // 나오는 html 경로
    }

    @PostMapping("/members") // PostMapping은 작성할 때, 넘어갈 때
    public String save(@ModelAttribute Member member){
        memberService.save(member);
        return "redirect:/members/list";
    }

    @GetMapping("/members/list")
    public String list(Model model){
        model.addAttribute("members",memberService.findAll());
        return  "members/list";
    }

    @GetMapping("/members/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        Member member = memberService.findById(id).orElseThrow();
        model.addAttribute("member",member);  // edit.html에 있는 form태그 안 object와 name이 일치해야된다. (GetMapping된 주소)
        return "members/edit";
    }

    @PostMapping("/members/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Member member){
        member.setId(id);
        memberService.save(member);
        return "redirect:/members/list";
    }

    @PostMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/members/list";
    }

}

// 스프링에서 필수요소 : controller, service(비지니스 로직), repository(저장소)
