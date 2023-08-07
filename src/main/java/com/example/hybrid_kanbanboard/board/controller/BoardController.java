package com.example.hybrid_kanbanboard.board.controller;

import com.example.hybrid_kanbanboard.board.dto.BoardRequestDto;
import com.example.hybrid_kanbanboard.board.dto.BoardResponseDto;
import com.example.hybrid_kanbanboard.board.service.BoardService;
import com.example.hybrid_kanbanboard.security.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hybrid")
public class BoardController {
    private final BoardService boardService;

    //보드 생성
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(requestDto, userDetails.getUser());
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoard() {
    return boardService.getBoard();
    }
}
