package com.example.hybrid_kanbanboard.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerificationRequestDto {
    private String userEmail;
    private int verificationCode;

    public EmailVerificationRequestDto(String userEmail, int verificationCode) {
        this.userEmail = userEmail;
        this.verificationCode = verificationCode;
    }
}
