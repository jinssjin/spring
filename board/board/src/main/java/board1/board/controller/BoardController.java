package board1.board.controller;

import board1.board.entity.Board;
import board1.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller Service, Repository : 컴포넌트(Component) 서버가 시작되면서 세가지를 등록함

@Controller
@RequestMapping("/board") // 주소에 공통적으로  /board를 넣어주겠다는 것
public class BoardController {

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("board", new Board());
        return "form";
    }

    @Autowired
    private BoardService boardService;

    @PostMapping("/save")
    public String save(Board board){
        boardService.save(board);
        return "redirect:/board";
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("boardList",boardService.findAll());
        return "list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        Board board = boardService.findById(id).orElseThrow();
        model.addAttribute("board",board);
        return "detail";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Board board = boardService.findById(id).orElseThrow();
        model.addAttribute("board",board);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Board board) {
        Board existing = boardService.findById(id).orElseThrow();
        existing.setTitle(board.getTitle());
        existing.setContent(board.getContent());
        boardService.save(existing); // 덮어쓰기 방식
        return "redirect:/board/"+id;
    }

    @GetMapping("/delete/{id}")
    public String detele(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board";
    }



}
