package com.example.hybrid_kanbanboard.board.controller;

import com.example.hybrid_kanbanboard.board.dto.BoardRequestDto;
import com.example.hybrid_kanbanboard.board.dto.BoardResponseDto;
import com.example.hybrid_kanbanboard.board.entity.Board;
import com.example.hybrid_kanbanboard.board.service.BoardService;
import com.example.hybrid_kanbanboard.security.jwt.UserDetailsImpl;
import com.example.hybrid_kanbanboard.status.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

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

    //보드 + 칼럼 조회
    @GetMapping("/board/{BoardId}")
    public BoardResponseDto getBoardAndCol(@PathVariable Long BoardId) {
        BoardResponseDto responseDto = boardService.getBoardCol(BoardId);
        return responseDto;
    }

    //특정 보드 선택 조회
//    @GetMapping("/board/{BoardId}")
//    public Board getBoardByNum(@PathVariable Long BoardId) {
//        Board responseDto = boardService.findBoard(BoardId);
//        return responseDto;
//    }

    //보드 수정
    @PutMapping("/board/{BoardId}")
    public ResponseEntity<MsgResponseDto> updateBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long BoardId, @RequestBody BoardRequestDto requestDto) {
        try {
            BoardResponseDto result = boardService.updateBoard(BoardId, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("수정이 완료되었습니다", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("보드 작성자만 수정할 수 있습니다.",HttpStatus.BAD_REQUEST.value()));
        }
    }
    //보드 삭제
    @DeleteMapping("/board/{BoardId}")
    public ResponseEntity<MsgResponseDto> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long BoardId) {
        try {
            boardService.deleteBoard(BoardId, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("보드가 삭제되었습니다", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("보드 작성자만 삭제할 수 있습니다.",HttpStatus.BAD_REQUEST.value()));
        }
    }

}
