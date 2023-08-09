package com.example.hybrid_kanbanboard.notification.specification;

import com.example.hybrid_kanbanboard.notification.entity.Notification;
import org.springframework.data.jpa.domain.Specification;

public class NotificationSpecification {

    public static Specification<Notification> hasUserId(final Long userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("userId"), userId);
        };
    }

    public static Specification<Notification> isUnread(final Boolean unread) {
        return (root, query, criteriaBuilder) -> {
            if (unread == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("isRead"), !unread);
        };
    }
}
