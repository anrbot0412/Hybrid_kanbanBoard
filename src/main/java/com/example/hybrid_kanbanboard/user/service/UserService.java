package com.example.hybrid_kanbanboard.user.service;

import com.example.hybrid_kanbanboard.user.dto.SignUpRequestDto;
import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import com.example.hybrid_kanbanboard.user.entity.User;
import com.example.hybrid_kanbanboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignUpRequestDto requestDto) {
        String userName = requestDto.getUserName();
        String userPassword = passwordEncoder.encode(requestDto.getUserPassword());
        String userEmail = requestDto.getUserEmail();
        String userNick = requestDto.getUserNick();
        UserRoleEnum role = requestDto.getRole();

        // 회원 중복 확인 --> Entity 에서도  @Column(nullable = false, unique = true) 이런식으로 고유값으로 선언해야한다
        Optional<User> checkUsername = userRepository.findByUserName(userName);
        if (checkUsername.isPresent()) { //isPresent() --> 데이터에 동일 데이터가 있는지 확인.
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        Optional<User> checkEmail = userRepository.findByUserEmail(userEmail);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 등록
        User user = new User(userName, userPassword, userEmail, userNick, role);
        userRepository.save(user);

    }
}
