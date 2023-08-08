package com.example.hybrid_kanbanboard.user.dto;

import com.example.hybrid_kanbanboard.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String userName;
    private String email;
    private String nickname;

    public UserResponseDto(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
