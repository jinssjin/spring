package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // JSON을 지원하는 controller
@Slf4j
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    // get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
                return (created != null) ? ResponseEntity.status(HttpStatus.CREATED).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    /*public Article create(@RequestBody ArticleForm dto){  // api에서 쓰는 어노테이션 (@RequestBody)
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }*/

    //patch(수정-업데이트)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);

        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
/*
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        
        // 1. dto를 entity로 변환
        Article article = dto.toEntity();
        log.info("id:{}, article:{}", id, article.toString());
        // 2. 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if(target == null || id != article.getId()){
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id:{}, article:{}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            */
/* return ResponseEntity.status(400).body(updated); *//*
 // 위와 같다
        }
        // 4. 업데이트 및 응답(Respon 200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
        */
/* return ResponseEntity.status(200).body(updated); *//*
 // 위와 같다
    }
*/

    //Delete
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);

        return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        // 204 삭제 후 응답이 필요없는 경우
    }
    /*public ResponseEntity<Article> delete(@PathVariable Long id){

        // 1. 대상찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/
}
