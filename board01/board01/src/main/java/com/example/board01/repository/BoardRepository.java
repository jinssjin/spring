package com.example.board01.repository;

import com.example.board01.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepositor<Entity 타입, Entit 대표 타입>
public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAll();
}
