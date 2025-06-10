package loginBoard.board2.Repository;

import loginBoard.board2.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository <Board,Long> {
    List<Board> findAllByOrderByIdDesc();
}
