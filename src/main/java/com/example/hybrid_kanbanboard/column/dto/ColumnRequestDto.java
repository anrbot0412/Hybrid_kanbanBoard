package com.example.hybrid_kanbanboard.column.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnRequestDto {

    private String columnName;
    private Long columnPosition;
}
