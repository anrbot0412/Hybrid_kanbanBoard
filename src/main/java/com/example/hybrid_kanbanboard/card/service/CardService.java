package com.example.hybrid_kanbanboard.card.service;

import com.example.hybrid_kanbanboard.card.dto.CardRequestDto;
import com.example.hybrid_kanbanboard.card.dto.CardResponseDto;
import com.example.hybrid_kanbanboard.card.entity.Card;
import com.example.hybrid_kanbanboard.card.repository.CardRepository;
import com.example.hybrid_kanbanboard.status.MsgResponseDto;
import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import com.example.hybrid_kanbanboard.user.entity.User;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;


    public void createCard(CardRequestDto requestDto, User user) {
        Card card = new Card(requestDto);
        card.setUser(user);

        cardRepository.save(card);
    }


    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {
        Card card = findCard(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || card.getUser().getUserId().equals(user.getUserId()))) {
            throw new RejectedExecutionException();
        }

        return new CardResponseDto(card.update(requestDto));
    }


    public void deleteCard(Long id, User user) {
        Card card = findCard(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || card.getUser().getUserId().equals(user.getUserId()))) {
            throw new RejectedExecutionException();
        }

        cardRepository.delete(card);
    }


    public Card findCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
