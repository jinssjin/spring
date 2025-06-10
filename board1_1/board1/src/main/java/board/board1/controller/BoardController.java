package board.board1.controller;

import board.board1.boardDto.BoardDTO;
import board.board1.entity.BoardEntity;
import board.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("boardDTO",new BoardDTO());
        return"create";
    }

    @PostMapping("/new")
    public String createAction(@ModelAttribute BoardDTO dto){
        boardService.create(dto);
        return "redirect:/";
    }
    
    // 게시글 목록 불러오기
    @GetMapping("/")
    public String openList(Model model){
        List<BoardEntity> boardEntityList = boardService.findAll();
        model.addAttribute("boards",boardEntityList);  // "게시글 뭉치에 붙어줄 이름", 불러올 매개변수
        return "list";
    }
    
    // 게시글 수정하기
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        BoardEntity boardEntity = boardService.findById(id);
        if(boardEntity == null) return "redirect:/";
        BoardDTO dto = new BoardDTO(boardEntity.getId(),boardEntity.getTitle(),boardEntity.getContent(),boardEntity.getWriter());
        model.addAttribute("boardDTO",dto);  // edit.html에서 th:object="${boardDTO}"로 받음
        return "/edit";
    }

    // 게시글 수정처리
    @PostMapping("{id}/edit")
    public String editAction(@PathVariable Long id, @ModelAttribute BoardDTO dto){
        boardService.update(id,dto);
        return "redirect:/";
    }

    @PostMapping("{id}/delete")
    public String deleteAction(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/";
    }
}
