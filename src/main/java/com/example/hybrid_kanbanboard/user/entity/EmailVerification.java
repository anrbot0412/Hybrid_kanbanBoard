package com.example.hybrid_kanbanboard.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "email_verifications")
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private int verificationCode;
    // 이메일 인증에 사용되는 인증 번호 저장

    @Column(nullable = false)
    private LocalDateTime expirationDateTime;
    // 이메일 인증 유효 기간

    public EmailVerification(String userEmail, int verificationCode, LocalDateTime expirationDateTime) {
        this.userEmail = userEmail;
        this.verificationCode = verificationCode;
        this.expirationDateTime = expirationDateTime;
    }
}
