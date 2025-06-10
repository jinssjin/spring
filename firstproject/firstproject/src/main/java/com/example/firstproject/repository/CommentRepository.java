package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    @Query(value="select * from comment where article_id = :articleId", nativeQuery = true)
    // :articleId - 검색할 변수 앞에 콜론(:)을 붙여줌, jpa 쿼리 문법

    List<Comment> findByArticleId(@Param("articleId") Long articleId);
    List<Comment> findByNickname(String nickname);

}
