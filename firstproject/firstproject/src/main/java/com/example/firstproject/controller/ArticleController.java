package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j  // 로깅을 위한 롬복 어노테이션
public class ArticleController {

    @Autowired  // 스프링부트가 미리 생성해놓은 Repository 객체를 가져옴(DI, Dependency Injection)
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        /* System.out.println("디티오에" + form.toString()); 아래와 같다(롬복 @Slf4j을 써서) */
        log.info("디티오에" + form.toString());
        // 1. dto를 entity로 변환
        Article article = form.toEntity();
        /* System.out.println("엔티티에" + article.toString()); 아래와 같다(롬복 @Slf4j을 써서) */
        log.info("엔티티에" + article.toString());
        // 2. repository에 entity를 저장
        Article saved = articleRepository.save(article);  // 임시데이터로 저장, submit 할 때마다 최신화, 과거 데이터 사라짐
        log.info("DB에" + saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){  // url에서 id를 변수로 받아옴
        log.info("id=" + id);
        // 1. id로 데이터를 가져옴 (게시글)
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 1. id로 데이터를 가져옴 (댓글)
        List<CommentDto> commentDtos = commentService.comments(id);
        // 2. 가져온 데이터를 모델에 등록 (게시글)
        model.addAttribute("article",articleEntity);
        // 2. 가져온 데이터를 모델에 등록 (댓글)
        model.addAttribute("commentDtos",commentDtos);
        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 view로 전달
        model.addAttribute("articleList",articleEntityList);
        // 3. view 페이지를 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("article",articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1. dto → entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. entity를 DB에 저장

        // 2-1. DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터가 있으면, 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        // 3. 수정 결과를 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");

        // 1. 삭제 대상을 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        
        // 2. 대상을 삭제
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }

        // 3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }

}