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
    //전체 보드 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getBoard() {
    return boardService.getBoard();
    }

    //특정 보드 선택 조회
    @GetMapping("/board/{BoardId}")
    public BoardResponseDto getBoardByNum(@PathVariable Long BoardId) {
        BoardResponseDto responseDto = boardService.findBoard(BoardId);
        return responseDto;
    }
}
