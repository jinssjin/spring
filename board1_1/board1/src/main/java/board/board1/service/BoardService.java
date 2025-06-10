package board.board1.service;

import board.board1.boardDto.BoardDTO;
import board.board1.entity.BoardEntity;
import board.board1.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void create(BoardDTO dto) {
        BoardEntity boardEntity = new BoardEntity(dto);
        boardRepository.save(boardEntity);
    }

    public List<BoardEntity> findAll() {
        return boardRepository.findAll();
    }

    public BoardEntity findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }


    public void update(Long id, BoardDTO dto) {
        Optional<BoardEntity> optionalBoard = boardRepository.findById(id);
        if(optionalBoard.isPresent()){
            BoardEntity boardEntity = optionalBoard.get(); // get으로 꺼내와서 형변환
            boardEntity.updateFormDTO(dto);
            boardRepository.save(boardEntity);
        }
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
