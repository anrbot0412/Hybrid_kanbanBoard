package com.example.hybrid_kanbanboard.card.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private String name;
    private String description;
    private String color;
    private String position;
    private String dueDate;
}
