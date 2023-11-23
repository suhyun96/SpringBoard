package com.example.spring_board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) //jpa에게 해당 엔티티가 auditing 기능을 쓴다는 거 알려줌
@Entity //객체를 jpa가 관리하는 엔티티로 지정 => 실제 db 테이블이랑 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected의 접근제어자를 가진 기본생성자를 자동 생성 엔티티는 기본생성자가 반드시 필요숮
@Getter
public class Article {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 옵션
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // title 컬럼이랑 매핑 not null
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder //빌더 패턴으로 객체 생성 -> 보다 직관적
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @CreatedDate //엔티티 생성 시간 저장용
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티 수정 시간 저장용
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 수정용
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
