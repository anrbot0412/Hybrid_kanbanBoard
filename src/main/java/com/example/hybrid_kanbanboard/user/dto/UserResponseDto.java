package com.example.hybrid_kanbanboard.user.dto;

import com.example.hybrid.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String userName;
    private String userEmail;
    private String userNick;

    public UserResponseDto(User user) {
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userNick = user.getUserNick();
    }
}
