package RestapiBoard.board1.controller;

import RestapiBoard.board1.dto.BoardDTO;
import RestapiBoard.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")

public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public Long create(@RequestBody BoardDTO dto){
        return boardService.save(dto);
    }

    @GetMapping
    public List<BoardDTO> list(){
        return boardService.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody BoardDTO dto){
        boardService.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }

}
