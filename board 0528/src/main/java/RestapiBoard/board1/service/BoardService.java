package RestapiBoard.board1.service;

import RestapiBoard.board1.dto.BoardDTO;
import RestapiBoard.board1.entity.Board;
import RestapiBoard.board1.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Long save(BoardDTO dto) {
        Board board = Board.builder()
                            .title(dto.getTitle())
                            .content(dto.getContent())
                            .writer(dto.getWriter())
                            .createdAt(LocalDateTime.now())
                            .build();
        return boardRepository.save(board).getId();
    }

    private static final DateTimeFormatter FORMMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");


    public List<BoardDTO> findAll() {
        return boardRepository.findAll().stream()
                .map((Board board) -> BoardDTO.builder()  // entity를 DTO로 변환
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt().format(FORMMATTER))
                .build())
                .collect(Collectors.toList());  // stream을 List로 변환
    }

    public Object findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt().format(FORMMATTER))
                .build();
    }

    public void update(Long id, BoardDTO dto) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setWriter(dto.getWriter());
        boardRepository.save(board);


    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
