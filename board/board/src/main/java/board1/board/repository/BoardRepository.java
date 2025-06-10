package board1.board.repository;

import board1.board.entity.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends CrudRepository<Board,Long> {

    List<Board> findAll();  // 형변환 Iterable → List
}
