package com.example.hybrid_kanbanboard.check.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CheckResponseDtos {
    private List<CheckResponseDto> checkResponseDtos;

    public CheckResponseDtos(List<CheckResponseDto> checkResponseDto) {
        this.checkResponseDtos = checkResponseDto;
    }
}
