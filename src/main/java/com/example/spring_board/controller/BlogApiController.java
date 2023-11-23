package com.example.spring_board.controller;

import com.example.spring_board.dto.AddArticleRequest;
import com.example.spring_board.dto.ArticleResponse;
import com.example.spring_board.dto.UpdateArticleRequest;
import com.example.spring_board.entity.Article;
import com.example.spring_board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController // http response body에 객체 데이터를 json형식으로 만들어서 반환
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    //@RequestBody : http 요청시 응답에 해당하는 값을 AddArticleReuqest에 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        Article saveArticle = blogService.save(addArticleRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {

        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(articles);

    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {

        Article article = blogService.update(id, request);

        return ResponseEntity.ok().body(article);
    }
}
