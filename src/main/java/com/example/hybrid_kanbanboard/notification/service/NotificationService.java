package com.example.hybrid_kanbanboard.notification.service;

import com.example.hybrid_kanbanboard.notification.entity.Notification;
import com.example.hybrid_kanbanboard.notification.repository.NotificationRepository;
import com.example.hybrid_kanbanboard.notification.specification.NotificationSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsForUser(Long userId, boolean unreadOnly, String sortBy, String order) {
        Specification<Notification> spec = Specification.where(NotificationSpecification.hasUserId(userId));
        if (unreadOnly) {
            spec = spec.and(NotificationSpecification.isUnread(true));
        }
        Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        return notificationRepository.findAll(spec, sort);
    }

    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
