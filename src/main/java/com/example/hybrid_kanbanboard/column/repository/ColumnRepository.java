package com.example.hybrid_kanbanboard.column.repository;

import com.example.hybrid_kanbanboard.column.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column, Long> {

}
