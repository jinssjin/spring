package loginBoard.board2.repository;

import loginBoard.board2.entity.Board;
import loginBoard.board2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardOrderByIdAsc(Board board);
}