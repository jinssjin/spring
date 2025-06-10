package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "article_id") // name은 생략 가능하다. 정확히 명시해주기위해 적어 준 것 뿐
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외발생
        if(dto.getId() != null)
            throw new IllegalCallerException("댓글생성실패! 댓글아이디가 없어야합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글생성실패! 게시글 아이디가 잘못되었습니다.");
        
        // 엔티티 생성 및 반환
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId()) throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력");
        // 객체를 갱신
        if(dto.getNickname() != null) this.nickname = dto.getNickname();
        if(dto.getBody() != null) this.body = dto.getBody();
    }
}
