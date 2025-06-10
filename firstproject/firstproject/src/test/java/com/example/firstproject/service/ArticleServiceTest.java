package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index() {
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        
        // 실제
        List<Article> articles = articleService.index();
        // 서비스에서 DB를 조회해서 articles 리스트를 가지고 옴
        System.out.println(expected.toString());
        System.out.println(articles.toString());

        // 검증
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공() {
        // 예상
        Long id =1L;
        Article expected = new Article(id,"가가가가","1111");

        // 실제
        Article article = articleService.show(id);

        // 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create() {
        // 예상
        String title = "라라라라";
        String content = "4444";

        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,title,content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(),article.toString());
    }
}