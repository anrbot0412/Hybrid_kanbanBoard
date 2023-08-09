package com.example.hybrid_kanbanboard.column.dto;

import com.example.hybrid_kanbanboard.column.entity.Columns;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponseDto {

    private Long BoardId;
    private Long ColumnId;
    private String columnName;
    private String columnMaker;

    public ColumnResponseDto(Columns columns) {
        this.ColumnId = columns.getColumnId();
        this.BoardId = columns.getBoard().getBoardId();
        this.columnMaker = columns.getUser().getUserName();
        this.columnName = columns.getColumnName();
    }


}
