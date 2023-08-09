package com.example.hybrid_kanbanboard.checkList.entity;

import com.example.hybrid_kanbanboard.check.entity.Check;
import com.example.hybrid_kanbanboard.checkList.dto.CheckListRequestDto;
import com.example.hybrid_kanbanboard.user.entity.TimeStamped;
import com.example.hybrid_kanbanboard.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckList extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkListId;

    @Column
    private String description;

    @Column
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check;

    public CheckList(CheckListRequestDto checkListRequestDto) {
        this.description = checkListRequestDto.getDescription();
        this.isCompleted = false;
    }

    public CheckList(CheckListRequestDto checkListRequestDto, User user, Check check) {
        this.description = checkListRequestDto.getDescription();
        this.isCompleted = false;
        this.user = user;
        this.check = check;
    }

    public void setIsCompleted() {
        this.isCompleted = !isCompleted;
    }

    public void updateCheckList(CheckListRequestDto checkListRequestDto) {
        this.description = checkListRequestDto.getDescription();
//        this.isCompleted;
    }
}
