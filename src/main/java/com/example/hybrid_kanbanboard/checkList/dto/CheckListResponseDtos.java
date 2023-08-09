package com.example.hybrid_kanbanboard.checkList.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CheckListResponseDtos {
    private List<CheckListResponseDto> checkListResponseDtos;

    public CheckListResponseDtos(List<CheckListResponseDto> checkListResponseDtos) {
        this.checkListResponseDtos = checkListResponseDtos;
    }
}
