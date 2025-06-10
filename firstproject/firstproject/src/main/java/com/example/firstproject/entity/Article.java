package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor   // 기본 생성자가 있어서 객체가 생성되어 넘어간다.
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


    public void patch(Article article) {
        if (article.title != null && !article.title.trim().isEmpty()) {
            this.title = article.title;
        }
        if (article.content != null && !article.content.trim().isEmpty()) {
            this.content = article.content;
        }
    }

}
