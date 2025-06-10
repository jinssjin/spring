package com.example.board01.dto;

import com.example.board01.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter  // Spring에서 Model에 객체를 넘길 때나, 다른 클래스에서 값을 읽어올 때 private 필드에는 직접 접근할 수 없기 때문입니다.
@Setter  // Spring MVC에서 @ModelAttribute를 통해 form 데이터를 바인딩할 때는 필드에 직접 접근하지 않고 setter 메서드를 호출합니다.
@Builder
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;

    public BoardDTO(Long id, String title, String content, String writer, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
    }

    public BoardDTO() {
    }

    // DTO를 Entity로 변환해주는 메서드
    /*public Board toEntity(){
        return new Board(id, title, content, writer, createdDate);
    }*/

    public Board toEntity(){
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .createdDate(createdDate)
                .build();
    }
}
