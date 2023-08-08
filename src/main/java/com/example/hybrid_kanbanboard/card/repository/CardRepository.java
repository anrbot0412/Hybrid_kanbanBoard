package com.example.hybrid_kanbanboard.card.repository;

import com.example.hybrid_kanbanboard.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
