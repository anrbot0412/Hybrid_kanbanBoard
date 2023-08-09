package com.example.hybrid_kanbanboard.card.service;

import com.example.hybrid_kanbanboard.card.dto.CardRequestDto;
import com.example.hybrid_kanbanboard.card.dto.CardResponseDto;
import com.example.hybrid_kanbanboard.card.entity.Card;
import com.example.hybrid_kanbanboard.card.repository.CardRepository;
import com.example.hybrid_kanbanboard.notification.entity.Notification;
import com.example.hybrid_kanbanboard.notification.repository.NotificationRepository;
import com.example.hybrid_kanbanboard.status.MsgResponseDto;
import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import com.example.hybrid_kanbanboard.user.entity.User;
import com.example.hybrid_kanbanboard.user.service.UserService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private UserService userService;
    private NotificationRepository notificationRepository;

    public void createCard(CardRequestDto requestDto, User user) {
        Card card = new Card(requestDto);
        card.setUser(user);

        // 알림 발송
        notifyCardUpdate(card, user);

        cardRepository.save(card);
    }


    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {
        Card card = findCard(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || card.getUser().getUserId().equals(user.getUserId()))) {
            throw new RejectedExecutionException();
        }

        // 알림 발송
        notifyCardUpdate(card, user);

        return new CardResponseDto(card.update(requestDto));
    }


    public void deleteCard(Long id, User user) {
        Card card = findCard(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || card.getUser().getUserId().equals(user.getUserId()))) {
            throw new RejectedExecutionException();
        }

        // 알림 발송
        notifyCardDeletion(card, user);

        cardRepository.delete(card);
    }


    public Card findCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

    public void notifyCardUpdate(Card card, User actor) {
        sendNotificationToBoardParticipants(card, actor, Notification.NotificationType.CARD_UPDATED);
    }

    public void notifyCardDeletion(Card card, User actor) {
        sendNotificationToBoardParticipants(card, actor, Notification.NotificationType.CARD_DELETED);
    }

    public void notifyCardMove(Card card, User actor) {
        sendNotificationToBoardParticipants(card, actor, Notification.NotificationType.CARD_MOVED);
    }

    private void sendNotificationToBoardParticipants(Card card, User actor, Notification.NotificationType type) {
        List<User> participants = userService.findParticipantsOfBoard(card.getColumns().getBoard().getBoardId());
        for (User participant : participants) {
            Notification notification = new Notification();
            notification.setType(type);
            notification.setMessage(
                    String.format("%s님이 카드 [%s]의 상태를 변경했습니다.", actor.getNickname(), card.getName()));
            notification.setRead(false);
            notification.setUserId(participant.getUserId());
            notification.setCardId(card.getCardId());
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);
        }
    }
}
