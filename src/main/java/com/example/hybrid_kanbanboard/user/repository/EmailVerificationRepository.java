package com.example.hybrid_kanbanboard.user.repository;

import com.example.hybrid_kanbanboard.user.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Long> {
    Optional<EmailVerification> findByUserEmail(String userMail);
}
