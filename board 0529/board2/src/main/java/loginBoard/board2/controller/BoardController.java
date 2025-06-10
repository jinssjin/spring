package loginBoard.board2.controller;

import jakarta.servlet.http.HttpSession;
import loginBoard.board2.Service.BoardService;
import loginBoard.board2.dto.BoardDTO;
import loginBoard.board2.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boards/write")
    public String writeForm(HttpSession session){
        if(session.getAttribute("loginUser")==null){
            return "redirect:/login";
        }
        return "board-write";
    }

    @PostMapping("/boards/write")
    public String write(BoardDTO dto, HttpSession session){
        Member writer = (Member) session.getAttribute("loginUser"); // session타입을  Member타입으로 형변환
        System.out.println(writer);
        if(writer == null){
            return "redirect:/login";
        }
        boardService.create(dto,writer);
        return "redirect:/boards";
    }
}
