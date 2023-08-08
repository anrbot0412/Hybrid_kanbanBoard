package com.example.hybrid_kanbanboard.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerificationRequestDto {
    private String email;
    private String verificationCode;

    public EmailVerificationRequestDto(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }
}
