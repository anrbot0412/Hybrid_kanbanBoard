package com.example.hybrid_kanbanboard.user.repository;

import com.example.hybrid_kanbanboard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String userEmail);

    @Query("SELECT u FROM User u JOIN u.boards b WHERE b.id = :boardId")
    List<User> findUsersByBoardId(@Param("boardId") Long boardId);
}