package com.example.spring_board.service;

import com.example.spring_board.dto.AddArticleRequest;
import com.example.spring_board.dto.UpdateArticleRequest;
import com.example.spring_board.entity.Article;
import com.example.spring_board.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor // final 붙거나 @NotNull 붙은 필드 생성자를 추가함
public class BlogService {

    private final BlogRepository blogRepository;

    //dto를 파라미터로 해서 받은 다음 save로 dto를 엔티티 객체로 변환후 save로 전달
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        // 조회 실패시 예외발생
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional //하나의 트랜젝션으로 묶음
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
