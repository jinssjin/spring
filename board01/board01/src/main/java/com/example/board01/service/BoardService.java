package com.example.board01.service;


import com.example.board01.dto.BoardDTO;
import com.example.board01.entity.Board;
import com.example.board01.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    // 가장 권장하는 방법
    // @RequiredArgsConstructor 어노테이션 해주고 final로 선언 (생성자 방식)

    // @Autowired와 같이 연결해주는 방법
    private final BoardRepository boardRepository;

    // 생성자 방식
    /*public void save(BoardDTO dto) {
        Board board = new Board(dto.getId(), dto.getTitle(),dto.getContent(),dto.getWriter(),LocalDateTime.now());
        boardRepository.save(board);
    }*/


    // Setter방식
    /*public void save(BoardDTO dto) {
        Board board = new Board();
        board.setId(dto.getId());
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setWriter(dto.getWriter());
        board.setCreatedDate(LocalDateTime.now());
        boardRepository.save(board);

    }*/

    // builder방식
    public void save(BoardDTO dto){
        boardRepository.save(dto.toEntity());
    }


    // Setter방식
   /* public List<BoardDTO> findAll() {
        return boardRepository.findAll().stream()
                .map(board -> {
                    BoardDTO dto = new BoardDTO();
                    dto.setId(board.getId());
                            dto.setTitle(board.getTitle());
                            dto.setContent(board.getContent());
                            dto.setWriter(board.getWriter());
                            dto.setCreatedDate(board.getCreatedDate());
                    return dto;
                }).collect(Collectors.toList());
    }*/

    // builder방식
    public List<BoardDTO> findAll() {
        return boardRepository.findAll().stream()
                .map(board -> BoardDTO.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .createdDate(board.getCreatedDate())
                        .build())
                        .collect(Collectors.toList());
    }



    /*public List<BoardDTO> findAll() {
        *//*List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boardList){
            BoardDTO dto = new BoardDTO(
            board.getId(),
            board.getTitle(),
            board.getContent(),
            board.getWriter(),
            board.getCreatedDate()
            );
            boardDTOList.add(dto);
        };
        return boardDTOList;*//*

        return boardRepository.findAll().stream()
                .map(board -> new BoardDTO(board.getId(), board.getTitle(), board.getContent(), board.getWriter(), board.getCreatedDate()))
                .collect(Collectors.toList());

    }*/


    // 생성자 방식
    /*public BoardDTO findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return new BoardDTO(board.getId(), board.getTitle(), board.getContent(), board.getWriter(), board.getCreatedDate());
    }*/


    // Setter방식
    /*public BoardDTO findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setWriter(board.getWriter());
        dto.setCreatedDate(board.getCreatedDate());
        return dto;
    }*/

    // builder방식
    public BoardDTO findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedDate())
                .build();
    }

    // 생성자 방식
    public void update(Long id, BoardDTO dto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("수정할 글 없음"));
        board.update(dto.getTitle(),dto.getContent());  // entity에서 update 메서드를 가질 때
        boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // Setter방식
    /*public void update(Long id, BoardDTO dto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("수정할 글 없음"));
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setWriter(dto.getWriter());
        boardRepository.save(board);
    }*/
    
    // 보안 상 메서드를 만들어서 사용하는 걸 권장


}
