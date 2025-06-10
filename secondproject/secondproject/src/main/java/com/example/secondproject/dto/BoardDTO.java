package com.example.secondproject.dto;

import com.example.secondproject.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardDTO {

    private Long id;
    private String title;
    private String content;

    public BoardEntity toEntity(){
        return new BoardEntity(id,title,content);
    }
}
