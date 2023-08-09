package com.example.hybrid_kanbanboard.user.entity;

import com.example.hybrid_kanbanboard.board.entity.Board;
import com.example.hybrid_kanbanboard.notification.entity.Notification;
import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    // 알림 기능 사용을 위한 보드와 사용자를 양방향 관계로 묶었습니다.

    @ManyToMany(mappedBy = "participants")
    private Set<Board> boards = new HashSet<>();

    // 알림과 유저 연관 관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    public User(String username, String password, String email, String Nick, UserRoleEnum role) {
        this.userName = username;
        this.password = password;
        this.nickname = Nick;
        this.email = email;
        this.role = role;
    }
}
