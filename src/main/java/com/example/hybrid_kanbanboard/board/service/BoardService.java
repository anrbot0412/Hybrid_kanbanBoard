package com.example.hybrid_kanbanboard.board.service;

import com.example.hybrid_kanbanboard.board.dto.BoardRequestDto;
import com.example.hybrid_kanbanboard.board.dto.BoardResponseDto;
import com.example.hybrid_kanbanboard.board.entity.Board;
import com.example.hybrid_kanbanboard.board.repository.BoardRepository;
import com.example.hybrid_kanbanboard.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 보드 생성
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = boardRepository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);
    }
    // 보드 조회
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }

    // 특정 보드 조회
    public BoardResponseDto findBoard(Long BoardId) {
        Board board =  boardRepository.findById(BoardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 보드가 존재하지 않습니다"));
        return new BoardResponseDto(board);
    }
}
