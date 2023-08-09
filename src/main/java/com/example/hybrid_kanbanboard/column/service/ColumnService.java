package com.example.hybrid_kanbanboard.column.service;

import com.example.hybrid_kanbanboard.board.entity.Board;
import com.example.hybrid_kanbanboard.board.service.BoardService;
import com.example.hybrid_kanbanboard.column.dto.ColumnRequestDto;
import com.example.hybrid_kanbanboard.column.dto.ColumnResponseDto;
import com.example.hybrid_kanbanboard.column.entity.Column;
import com.example.hybrid_kanbanboard.column.repository.ColumnRepository;
import com.example.hybrid_kanbanboard.user.dto.UserRoleEnum;
import com.example.hybrid_kanbanboard.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardService boardService;

    // 칼럼 생성
    @Transactional
    public ColumnResponseDto makeColumn(ColumnRequestDto requestDto, User user, Long BoardId) {
        Board board = boardService.findBoard(BoardId);
        Column column = columnRepository.save(new Column(requestDto, user, board));

        board.addColumnList(column);
        List<Column> colList = board.getColumnList();
        for (Column c : colList) {
            System.out.println("Column = " + c.getColumnName());
        }
        return new ColumnResponseDto(column);
    }

    // 칼럼 삭제
    @Transactional
    public void deleteColumn(Long ColumnId, Long BoardId, User user) {
        String username = findColumn(ColumnId).getUser().getUserName();
        Board board = boardService.findBoard(BoardId);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || username.equals(user.getUserName()))) {
            throw new RejectedExecutionException();
        } else
            columnRepository.deleteById(ColumnId);
        List<Column> columnList = board.getColumnList();
        columnList.removeIf(c -> c.getColumnId() == ColumnId); // equal 을 써야하나?

        for (Column c : columnList) {
            System.out.println("Column = " + c.getColumnId());
        }
    }

    // 칼럼 수정
    @Transactional
    public ColumnResponseDto updateColumn(Long ColumnId, Long BoardId, ColumnRequestDto requestDto, User user) {
        String username = findColumn(ColumnId).getUser().getUserName();
        Board board = boardService.findBoard(BoardId);
        Column column = findColumn(ColumnId);

        if (username.equals(user.getUserName())|| user.getRole().toString().equals("ADMIN")) {
            List<Column> columnList = board.getColumnList();
            column.update(requestDto);
            return new ColumnResponseDto(column);
        } else {
            System.out.println("수정 권한이 없습니다");
            return null;
        }

    }

    // 칼럼 검색
    private Column findColumn(Long ColumnId) {
        return columnRepository.findById(ColumnId).orElseThrow(() ->
                new IllegalArgumentException("해당 칼럼이 존재하지 않습니다."));
    }
}
