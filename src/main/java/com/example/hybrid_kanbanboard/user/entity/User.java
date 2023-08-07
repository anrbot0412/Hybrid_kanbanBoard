package com.example.hybrid_kanbanboard.user.entity;

import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userNick;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, String email, String Nick, UserRoleEnum role) {
        this.userName = username;
        this.userPassword = password;
        this.userNick = Nick;
        this.userEmail = email;
        this.role = role;
    }
}
