package com.example.hybrid_kanbanboard.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgResponseDto {

    private String msg;
    private Integer statusCode;

    public MsgResponseDto(String msg, Integer statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }

}