package member.member1.controller;

import member.member1.dto.MemberDto;
import member.member1.entity.Member;
import member.member1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // 어노테이션, 스프링부트에 컨트롤러로 등록
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model){
        // 비어있는 MemberDto객체를 파일로 전달
        model.addAttribute("memberDto", new MemberDto());
        return "members/create";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute MemberDto dto){
        memberService.create(dto);
        return "redirect:/members";
    }

    // read - 회원목록(list)
    @GetMapping
    public String list(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);  // members를 화면에 뿌려주는 변수로 이동
        return "members/list";
    }

    // update - 수정폼 불러오기
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        Member member = memberService.findById(id);
        if(member == null){
            return "redirect:/members";
        }
        MemberDto dto = new MemberDto(member.getId(),member.getUsername(),member.getEmail(),member.getPassword());
        model.addAttribute("memberDto",dto);
        return "members/edit";
    }
    
    // update - 수정 처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute MemberDto dto){
        memberService.update(id,dto);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        memberService.delete(id);
        return "redirect:/members";
    }
}

