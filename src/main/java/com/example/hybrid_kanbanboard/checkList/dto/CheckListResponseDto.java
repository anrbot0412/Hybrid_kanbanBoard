package com.example.hybrid_kanbanboard.checkList.dto;

import com.example.hybrid_kanbanboard.checkList.entity.CheckList;
import lombok.Getter;

@Getter
public class CheckListResponseDto {
    private String description;
    private boolean isCompleted;
    public CheckListResponseDto(CheckList checkList) {
        this.description = checkList.getDescription();
        this.isCompleted = checkList.isCompleted();
    }
}
