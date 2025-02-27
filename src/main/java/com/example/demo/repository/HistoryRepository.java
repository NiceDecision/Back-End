package com.example.demo.repository;

import com.example.demo.domain.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByUserId(Long userId);
}
