package org.example.englishforum.repository;

import org.example.englishforum.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByUserId(Long userId);

    List<Report> findAllByPostId(Long postId);
}
