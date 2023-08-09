package com.example.hybrid_kanbanboard.comment.service;

import com.example.hybrid_kanbanboard.card.entity.Card;
import com.example.hybrid_kanbanboard.comment.dto.CommentListResponseDto;
import com.example.hybrid_kanbanboard.comment.dto.CommentRequestDto;
import com.example.hybrid_kanbanboard.comment.dto.CommentResponseDto;
import com.example.hybrid_kanbanboard.comment.entity.Comment;
import com.example.hybrid_kanbanboard.comment.repository.CommentRepository;
import com.example.hybrid_kanbanboard.notification.entity.Notification;
import com.example.hybrid_kanbanboard.notification.repository.NotificationRepository;
import com.example.hybrid_kanbanboard.user.entity.User;
import com.example.hybrid_kanbanboard.user.repository.UserRepository;
import com.example.hybrid_kanbanboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private UserService userService;
    private NotificationRepository notificationRepository;

//    private final CardRepository cardRepository;

    @Transactional
    public void createComment(Long cardId, User user, CommentRequestDto commentRequestDto) {
//        Card card = cardRepository.findById(cardId).orElseThrow();

        //받아온 user를 UserBoard에 있는지 조건문처리

        Comment comment = new Comment(commentRequestDto);
        comment.setUser(user);
//        comment.setCard(card);

        // 알림 서비스를 위한 코드 TODO : 카드 id 처리 해야함
        // notifyCardComment(cardId, user);
    }

    @Transactional
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = findComment(commentId);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentListResponseDto getComments() {
        List<CommentResponseDto> commentListResponseDto = commentRepository.findAllByOrderByModifiedAtDesc()
                .stream().map(CommentResponseDto::new).collect(Collectors.toList());
        return new CommentListResponseDto(commentListResponseDto);
    }

    @Transactional
    public void updateComment(Long commentId, User user, CommentRequestDto commentRequestDto) {
        Comment comment = findComment(commentId);

        if (!comment.getUser().getUserName().equals(user.getUserName())) {
            //에외처리
        }
        comment.updateComment(commentRequestDto);
    }

    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);

        if (!comment.getUser().getUserName().equals(user.getUserName())) {
            //에외처리
        }
        commentRepository.delete(comment);
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글이 존재하지 않습니다."));
    }

    // 알림 서비스 기능

    public void notifyCardComment(Card card, User actor) {
        sendNotificationToBoardParticipants(card, actor, Notification.NotificationType.COMMENT_ADDED);
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
