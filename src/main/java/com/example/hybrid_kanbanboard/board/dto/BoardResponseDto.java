package com.example.hybrid_kanbanboard.board.dto;

import com.example.hybrid_kanbanboard.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long BoardId;
    private String description;
    private String name;

    public BoardResponseDto(Board board) {
        this.BoardId = board.getBoardId();
        this.description= board.getDescription();
        this.name = board.getName();

    }
}
