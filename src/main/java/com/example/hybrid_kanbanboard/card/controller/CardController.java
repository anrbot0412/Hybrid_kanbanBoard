package com.example.hybrid_kanbanboard.card.controller;

import com.example.hybrid_kanbanboard.card.dto.CardRequestDto;
import com.example.hybrid_kanbanboard.card.dto.CardResponseDto;
import com.example.hybrid_kanbanboard.card.service.CardService;
import com.example.hybrid_kanbanboard.security.jwt.UserDetailsImpl;
import com.example.hybrid_kanbanboard.status.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/")
    public ResponseEntity<MsgResponseDto> createCard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody CardRequestDto requestDto) {
        cardService.createCard(requestDto, userDetails.getUser());

        return ResponseEntity.ok().body(new MsgResponseDto("카드 생성 성공!", HttpStatus.OK.value()));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<MsgResponseDto> updateCard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long cardId,
                                                     @RequestBody CardRequestDto requestDto) {
        try {
            CardResponseDto result = cardService.updateCard(cardId, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }


    @DeleteMapping("/{cardId}")
    public ResponseEntity<MsgResponseDto> deleteCard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long cardId) {
        try {
            cardService.deleteCard(cardId, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("카드 삭제 성공,", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }





}
