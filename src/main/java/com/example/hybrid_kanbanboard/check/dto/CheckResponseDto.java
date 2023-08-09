package com.example.hybrid_kanbanboard.check.dto;

import com.example.hybrid_kanbanboard.check.entity.Check;

public class CheckResponseDto {
    private String title;

    public CheckResponseDto(Check check) {
        this.title = check.getTitle();
    }
}
