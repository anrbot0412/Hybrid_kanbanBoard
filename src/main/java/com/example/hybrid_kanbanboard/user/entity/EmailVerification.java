//package com.example.hybrid_kanbanboard.user.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Entity
//@Setter
//@NoArgsConstructor
//@Table(name = "email_verifications")
//public class EmailVerification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long emailId;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private int verificationCode;
//    // 이메일 인증에 사용되는 인증 번호 저장
//
//    @Column(nullable = false)
//    private LocalDateTime expirationDateTime;
//    // 이메일 인증 유효 기간
//
//    private boolean isApproved = false;
//
//    public EmailVerification(String email, int verificationCode, LocalDateTime expirationDateTime) {
//        this.email = email;
//        this.verificationCode = verificationCode;
//        this.expirationDateTime = expirationDateTime;
//    }
//
//    public void approve() {
//        this.isApproved = true;
//        // 인증이 됐으면 true가 되도록!
//    }
//}
