package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    /* JpaRepository; 페이지 나누기, 정렬 가능 */

    @Override
    ArrayList<Article> findAll();
}
