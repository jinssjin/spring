package loginBoard.board2.service;

import loginBoard.board2.dto.CommentDTO;
import loginBoard.board2.entity.Board;
import loginBoard.board2.entity.Comment;
import loginBoard.board2.entity.Member;
import loginBoard.board2.repository.BoardRepository;
import loginBoard.board2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    public void addComment(Board board, Member writer, CommentDTO dto) {
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .writer(writer)
                .board(board)
                .createdAt(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
    }

    public List<Comment> getComments(Board board) {
        return commentRepository.findByBoardOrderByIdAsc(board);
    }

    public void saveComment(Long boardId, String content, Member writer) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setBoard(board);

        commentRepository.save(comment);
    }
}
