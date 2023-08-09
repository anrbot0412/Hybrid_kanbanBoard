package com.example.hybrid_kanbanboard.column.controller;

import com.example.hybrid_kanbanboard.column.dto.ColumnRequestDto;
import com.example.hybrid_kanbanboard.column.dto.ColumnResponseDto;
import com.example.hybrid_kanbanboard.column.service.ColumnService;
import com.example.hybrid_kanbanboard.security.jwt.UserDetailsImpl;
import com.example.hybrid_kanbanboard.status.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping(value ="/hybrid")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    //칼럼 생성
    @PostMapping(value = "/column")
    public ColumnResponseDto makeColumn(@RequestBody ColumnRequestDto requestDto, @RequestParam Long BoardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return columnService.makeColumn(requestDto, userDetails.getUser(), BoardId);
    }

    //칼럼 삭제

    @DeleteMapping("/column/{ColumnId}")
    public ResponseEntity<MsgResponseDto> deleteColumn(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long ColumnId, @RequestParam Long BoardId) {
        try {
        columnService.deleteColumn(ColumnId,BoardId,userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("칼럼이 삭제되었습니다.", HttpStatus.OK.value()));
        }catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 칼럼 수정

    @PutMapping("/column/{ColumnId}")
    public ColumnResponseDto updateColumn(@PathVariable Long ColumnId, @RequestParam Long BoardId, @RequestBody ColumnRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return columnService.updateColumn(ColumnId,BoardId,requestDto,userDetails.getUser());
    }
}
