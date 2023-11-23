package com.example.spring_board.repository;

import com.example.spring_board.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// 엔티티타입 , PK타입
public interface BlogRepository extends JpaRepository<Article,Long> {
}
