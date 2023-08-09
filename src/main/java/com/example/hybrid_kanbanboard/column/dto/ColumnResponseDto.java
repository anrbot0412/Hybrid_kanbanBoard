package com.example.hybrid_kanbanboard.column.dto;

import com.example.hybrid_kanbanboard.column.entity.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponseDto {

    private Long BoardId;
    private Long ColumnId;
    private String columnName;
    private String columnMaker;

    public ColumnResponseDto(Column column) {
        this.ColumnId = column.getColumnId();
        this.BoardId = column.getBoard().getBoardId();
        this.columnMaker = column.getUser().getUserName();
        this.columnName = column.getColumnName();
    }


}
