package com.example.secondproject.controller;

import com.example.secondproject.dto.BoardDTO;
import com.example.secondproject.entity.BoardEntity;
import com.example.secondproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/new")
    public String writeForm(){
        return "write";
    }

    @PostMapping("/create")
    public String writeAction(BoardDTO saveDTO){
    BoardEntity saveEntity = saveDTO.toEntity();
    BoardEntity savedEntity = boardService.save(saveEntity);
    return "redirect:/" + savedEntity.getId();
    }
}
