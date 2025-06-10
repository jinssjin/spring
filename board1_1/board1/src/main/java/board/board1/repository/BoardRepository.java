package board.board1.repository;

import board.board1.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<BoardEntity,Long> {

    // Iterable타입을 List타입으로 오버라이딩
    List<BoardEntity> findAll();
}
