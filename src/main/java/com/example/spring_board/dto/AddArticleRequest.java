package com.example.spring_board.dto;

import com.example.spring_board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본 생성ㅇ자 추가
@AllArgsConstructor // 모든 필드값 파라미터로 받는 생성자 추가
@Getter
// 데이터 옮기기용 -> 별도 비즈니스로직 x
public class AddArticleRequest {

    private String title;
    private String content;

    // 생성자로 객체 생성 -> dto를 entity로 만들어서 블로그 글 추가시 저장용 엔티티 변환할 떄 씀
    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
