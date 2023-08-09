package com.example.hybrid_kanbanboard.board.dto;

import com.example.hybrid_kanbanboard.board.entity.Board;
import com.example.hybrid_kanbanboard.column.dto.ColumnResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {
    private Long BoardId;
    private String description;
    private String boardName;
    private String boardMaker;
    private List<ColumnResponseDto> columnList;

    public BoardResponseDto(Board board) {
        this.BoardId = board.getBoardId();
        this.description= board.getDescription();
        this.boardName = board.getBoardName();
        this.boardMaker = board.getUser().getUserName();
        this.columnList = board.getColumnsList().stream()
                .map(ColumnResponseDto::new)
                .toList();

    }
}
