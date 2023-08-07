package com.example.hybrid_kanbanboard.user.service;

import com.example.hybrid_kanbanboard.user.dto.EmailVerificationRequestDto;
import com.example.hybrid_kanbanboard.user.dto.SignUpRequestDto;
import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import com.example.hybrid_kanbanboard.user.entity.EmailVerification;
import com.example.hybrid_kanbanboard.user.entity.User;
import com.example.hybrid_kanbanboard.user.repository.EmailVerificationRepository;
import com.example.hybrid_kanbanboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationRepository emailVerificationRepository;

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
        Optional<User> checkUserEmail = userRepository.findByUserEmail(userEmail);
        if (checkUserEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 등록
        User user = new User(userName, userPassword, userEmail, userNick, role);
        userRepository.save(user);
    }

    // 메서드 (EmailVerificationRequestDto emailVerificationRequestDto)

    // 이메일 인증을 확인
//    boolean isVerified = VerificationCodeCheck(emailVerificationRequestDto);
//        if (!isVerified) {
//        throw new IllegalArgumentException("이메일 인증이 필요합니다.");

    // 이메일 인증 코드를 확인
    public boolean VerificationCodeCheck (EmailVerificationRequestDto emailVerificationRequestDto) {
        String userEmail = emailVerificationRequestDto.getUserEmail();
        int userEnteredVerificationCode = emailVerificationRequestDto.getVerificationCode();

        Optional<EmailVerification> emailVerification = emailVerificationRepository.findByUserEmail(userEmail);
        // findByEmail 메서드는 이메일 인증 정보가 존재하면 해당 정보를 Optional로 감싸서 반환하고, 존재하지 않으면 빈 Optional을 반

        if (emailVerification.isPresent()) { // isPresent() 메서드는 Optional<T> 객체에 값이 존재하면 true를 반환하고, 값이 없으면 false를 반환
            // isPresent()를 사용하여 emailVerification 객체에 값이 있는지 여부를 확인

            EmailVerification verification = emailVerification.get(); // get() 메서드는 Optional 객체의 값을 반환, 이전에 isPresent()를 사용하여 값이 있는지 확인해서 이 곳에서는 값이 반드시 존재
            // emailVerification.get()을 사용하여 emailVerification 객체에 감싸진 이메일 인증 정보(EmailVerification)를 가져옴

            if (verification.getVerificationCode() != userEnteredVerificationCode) {
                // 저장된 인증 코드(verification.getVerificationCode())와 사용자가 입력한 인증 코드(userEnteredVerificationCode)를 비교하여 일치하지 않으면 throw
                throw new IllegalArgumentException("올바른 인증 번호가 아닙니다.");
            }
            if (verification.getExpirationDateTime().isBefore(LocalDateTime.now())) {
                // verification.getExpirationDateTime()를 사용하여 이메일 인증 정보의 인증 코드 유효 기간(expirationDateTime)을 가져오기
                // 현재 시간(LocalDateTime.now())과 .isBefore를 사용하여 비교하고 throw
                // (verification.getExpirationDateTime()이 LocalDateTime.now()보다 .isBefore(지났다면)?
                throw new IllegalArgumentException("인증 시간이 만료되었습니다.");
            }

        } else {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        }
        return true;
    }
}
