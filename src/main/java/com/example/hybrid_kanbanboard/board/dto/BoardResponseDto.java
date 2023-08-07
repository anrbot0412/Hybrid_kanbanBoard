package com.example.hybrid_kanbanboard.board.dto;

import com.example.hybrid_kanbanboard.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private int BoardId;
    private String description;
    private String name;

    public BoardResponseDto(Board board) {

    }
}
