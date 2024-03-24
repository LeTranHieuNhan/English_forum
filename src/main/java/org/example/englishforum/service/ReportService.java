package org.example.englishforum.service;

import org.example.englishforum.dto.ReportDto;

import java.util.List;

public interface ReportService {
    ReportDto saveReport(ReportDto reportDto, Long userId, Long postId);

    ReportDto getReportById(Long id);

    ReportDto updateReport(ReportDto reportDto , Long id );

    void deleteReport(Long id);

    List<ReportDto> getAllReports();

    List<ReportDto> getReportsByUserId(Long userId);

    List<ReportDto> getReportsByPostId(Long postId);

}
