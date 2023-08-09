package com.example.hybrid_kanbanboard.card.entity;

import com.example.hybrid_kanbanboard.card.dto.CardRequestDto;
import com.example.hybrid_kanbanboard.check.entity.Check;
import com.example.hybrid_kanbanboard.column.entity.Columns;
import com.example.hybrid_kanbanboard.notification.entity.Notification;
import com.example.hybrid_kanbanboard.user.entity.TimeStamped;
import com.example.hybrid_kanbanboard.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Card extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String color;

    @Column
    private String position;

    @Column
    private String dueDate;

    // 수정해야댐~~~
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private Columns columns;

    @OneToMany(mappedBy = "card")
    private List<Check> checkList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    public Card(CardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.color = requestDto.getColor();
        this.position = requestDto.getPosition();
        this.dueDate = requestDto.getDueDate();

    }

    public Card update(CardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.color = requestDto.getColor();
        this.position = requestDto.getPosition();
        this.dueDate = requestDto.getDueDate();

        return this;
    }

    public void addCheck(Check check) {
        this.checkList.add(check);
        check.setCard(this);
    }
}
