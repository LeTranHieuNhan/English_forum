package org.example.englishforum.controller;

import org.example.englishforum.dto.ReportDto;
import org.example.englishforum.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReportControllerTest {

    @Mock
    ReportService reportService;

    @InjectMocks
    ReportController reportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save report and return created status")
    void saveReport() {
        ReportDto reportDto = new ReportDto();
        when(reportService.saveReport(reportDto, 1L, 1L)).thenReturn(reportDto);

        ResponseEntity<ReportDto> response = reportController.saveReport(reportDto, 1L, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reportDto, response.getBody());
    }

    @Test
    @DisplayName("Should get report by id and return ok status")
    void getReportById() {
        ReportDto reportDto = new ReportDto();
        when(reportService.getReportById(1L)).thenReturn(reportDto);

        ResponseEntity<ReportDto> response = reportController.getReportById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDto, response.getBody());
    }

    @Test
    @DisplayName("Should update report and return ok status")
    void updateReport() {
        ReportDto reportDto = new ReportDto();
        when(reportService.updateReport(reportDto, 1L)).thenReturn(reportDto);

        ResponseEntity<ReportDto> response = reportController.updateReport(reportDto, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDto, response.getBody());
    }

    @Test
    @DisplayName("Should delete report and return no content status")
    void deleteReport() {
        doNothing().when(reportService).deleteReport(1L);

        ResponseEntity<Void> response = reportController.deleteReport(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Should get all reports and return ok status")
    void getAllReports() {
        List<ReportDto> reportDtoList = Collections.singletonList(new ReportDto());
        when(reportService.getAllReports()).thenReturn(reportDtoList);

        ResponseEntity<List<ReportDto>> response = reportController.getAllReports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should get reports by user id and return ok status")
    void getReportsByUserId() {
        List<ReportDto> reportDtoList = Collections.singletonList(new ReportDto());
        when(reportService.getReportsByUserId(1L)).thenReturn(reportDtoList);

        ResponseEntity<List<ReportDto>> response = reportController.getReportsByUserId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should get reports by post id and return ok status")
    void getReportsByPostId() {
        List<ReportDto> reportDtoList = Collections.singletonList(new ReportDto());
        when(reportService.getReportsByPostId(1L)).thenReturn(reportDtoList);

        ResponseEntity<List<ReportDto>> response = reportController.getReportsByPostId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDtoList, response.getBody());
    }
}
