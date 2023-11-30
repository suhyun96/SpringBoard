package com.example.spring_board.config;

import com.example.spring_board.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    @Bean  // 해당 정적 리소스는 스테틱 파일 쓰지 않도록 설정한 것
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .antMatchers("/static/**");
    }

    @Bean // 특정 웹 페이지 요청에 대한 보안 구성
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/user").permitAll()  // login signup user는 인가 없이 접근 가능
                .anyRequest().authenticated() // 위에 지정한 url외에는 인증기능 설정
                .and()
                .formLogin() // 폼 기반 로그인 설정
                .loginPage("/login") // 로그인 페이지 경로 설정
                .defaultSuccessUrl("/articles") // 성공했을 때 나오는 페이지 설정
                .and()
                .logout()
                .logoutSuccessUrl("/login") // 로그아웃 성공시 페이지 지정
                .invalidateHttpSession(true) // 로그아웃 시 세션을 전체 삭제
                .and()
                .csrf().disable() //csrf 비활성화
                .build();
    }

    // 인증 관리자 설정 사용자 정보 가져올 서비스 지정 혹은 인증방법 기등 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // 사용자 정보 가져올 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder) //패스워드 암호화 인코더
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}