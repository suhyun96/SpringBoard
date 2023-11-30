package com.example.spring_board.service;

import com.example.spring_board.dto.AddUserRequest;
import com.example.spring_board.entity.User;
import com.example.spring_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder().email(dto.getEmail()).
                password(bCryptPasswordEncoder.encode(dto.getPassword())).
                build()).getId();

    }
}
