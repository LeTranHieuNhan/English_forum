package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.ReportDto;
import org.example.englishforum.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<ReportDto> saveReport(@RequestBody ReportDto reportDto,
                                                @PathVariable("userId") Long userId,
                                                @PathVariable("postId") Long postId) {
        ReportDto savedReport = reportService.saveReport(reportDto, userId, postId);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable("id") Long id) {
        ReportDto report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> updateReport(@RequestBody ReportDto reportDto, @PathVariable("id") Long id) {
        ReportDto updatedReport = reportService.updateReport(reportDto, id);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable("id") Long id) {
        reportService.deleteReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<ReportDto> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReportDto>> getReportsByUserId(@PathVariable("userId") Long userId) {
        List<ReportDto> reports = reportService.getReportsByUserId(userId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<ReportDto>> getReportsByPostId(@PathVariable("postId") Long postId) {
        List<ReportDto> reports = reportService.getReportsByPostId(postId);
        return ResponseEntity.ok(reports);
    }
}
