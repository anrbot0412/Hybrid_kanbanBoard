package com.example.hybrid_kanbanboard.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String userName;
    private String userPassword;
    private String userEmail;
    private String userNick;
}
