package com.example.hybrid_kanbanboard.notification.entity;

import com.example.hybrid_kanbanboard.card.entity.Card;
import com.example.hybrid_kanbanboard.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;

    @Column(name = "is_read")
    private boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "card_id")
    private Card cardId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum NotificationType {
        CARD_UPDATED,
        CARD_DELETED,
        CARD_MOVED,
        COMMENT_ADDED
    }
}
