package com.example.hybrid_kanbanboard.check.entity;

import com.example.hybrid_kanbanboard.check.dto.CheckRequestDto;
import com.example.hybrid_kanbanboard.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Check {
    @Id
    @GeneratedValue
    private Long CheckId;

    @Column
    private String title;

//    @ManyToOne
//    @JoinColumn
//    private Card card;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public Check(CheckRequestDto checkRequestDto) {
        this.title = checkRequestDto.getTitle();
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void updateCheckList(CheckRequestDto checkRequestDto) {
        this.title = checkRequestDto.getTitle();
    }
}
