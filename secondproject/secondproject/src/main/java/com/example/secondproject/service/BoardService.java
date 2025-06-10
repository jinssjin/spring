package com.example.secondproject.service;

import com.example.secondproject.entity.BoardEntity;
import com.example.secondproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public BoardEntity save(BoardEntity board) {
        boardRepository.save(board);
        return board;
    }
}
