package com.example.hybrid_kanbanboard.board.service;

import com.example.hybrid_kanbanboard.board.dto.BoardRequestDto;
import com.example.hybrid_kanbanboard.board.dto.BoardResponseDto;
import com.example.hybrid_kanbanboard.board.entity.Board;
import com.example.hybrid_kanbanboard.board.repository.BoardRepository;
import com.example.hybrid_kanbanboard.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board =boardRepository.save(new Board(requestDto, user));
        return new BoardResponseDto(board);

    }
}
