package com.example.hybrid_kanbanboard.comment.dto;

import java.util.List;

public class CommentListResponseDto {
    List<CommentResponseDto> commentListResponseDto;

    public CommentListResponseDto(List<CommentResponseDto> commentListResponseDto) {
        this.commentListResponseDto = commentListResponseDto;
    }
}
