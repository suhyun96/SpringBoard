package com.example.spring_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // created_at , updated_at 자동 업뎃용
@SpringBootApplication
public class SpringBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardApplication.class, args);
    }

}
