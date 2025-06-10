package RestapiBoard.board1.controller;

import RestapiBoard.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasicController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/form")
    public String formPage(){
        return "form";
    }

    // 자바 스크립트를 이용한 방식
    @GetMapping("/list")
    public String formPage1(){
        return "list";
    }

    //
    @GetMapping("/list1")
    public String formPage1(Model model){
        model.addAttribute("boards",boardService.findAll());
        return "list1";
    }

    @GetMapping("/{id}")
    public String updatePage(@RequestParam Long id, Model model){
        model.addAttribute("board",boardService.findById(id));
        return  "update";
    }

    // list1에서 "@{'/update/' + ${board.id}}"으로 받을 경우
    /*@GetMapping("/update/{id}")
    public String updatePage(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "update";
    }*/

    @GetMapping("detail/{id}")
    public String detailPage(@PathVariable Long id, Model model){
        model.addAttribute("board",boardService.findById(id));
        return  "detail";
    }
}
