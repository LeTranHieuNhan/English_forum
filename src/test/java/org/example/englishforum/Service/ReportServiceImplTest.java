package org.example.englishforum.service.impl;

import org.example.englishforum.dto.ReportDto;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.Report;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.PostRepository;
import org.example.englishforum.repository.ReportRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.utils.GenericMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ReportServiceImplTest {



        @Mock
        private ReportRepository reportRepository;

        @Mock
        private PostRepository postRepository;

        @Mock
        private UserRepository userRepository;

        @Mock
        private GenericMapper genericMapper;

        @InjectMocks
        private ReportServiceImpl reportService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.initMocks(this);
        }


    @Test
    void testSaveReport() {
        // Given
        ReportDto reportDto = new ReportDto();
        User user = new User();
        Post post = new Post();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(genericMapper.map(any(), eq(Report.class))).thenReturn(new Report());

        // When
        ReportDto savedReport = reportService.saveReport(reportDto, 1L, 2L);

        // Then
        verify(reportRepository, times(1)).save(any(Report.class));
        assertEquals(reportDto, savedReport);
    }

    @Test
    void testGetReportById() {
        // Given
        Report report = new Report();
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(report));
        when(genericMapper.map(any(), eq(ReportDto.class))).thenReturn(new ReportDto());

        // When
        ReportDto retrievedReport = reportService.getReportById(1L);

        // Then
        assertEquals(new ReportDto(), retrievedReport);
    }

    @Test
    void testUpdateReport() {
        // Given
        ReportDto reportDto = new ReportDto();
        reportDto.setContent("Updated content");
        Report report = new Report();
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(report));
        when(genericMapper.map(any(), eq(Report.class))).thenReturn(new Report());

        // When
        ReportDto updatedReport = reportService.updateReport(reportDto, 1L);

        // Then
        verify(reportRepository, times(1)).save(any(Report.class));
        assertEquals(reportDto.getContent(), updatedReport.getContent());
    }

    @Test
    void testDeleteReport() {
        // Given
        Report report = new Report();
        User user = new User();
        Post post = new Post();
        user.getReports().add(report);
        post.getReports().add(report);
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(report));
        when(report.getUser()).thenReturn(user);
        when(report.getPost()).thenReturn(post);

        // When
        reportService.deleteReport(1L);

        // Then
        verify(reportRepository, times(1)).deleteById(anyLong());
        verify(postRepository, times(1)).save(any(Post.class));
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(0, user.getReports().size());
        assertEquals(0, post.getReports().size());
    }

    @Test
    void testGetAllReports() {
        // Given
        List<Report> reports = new ArrayList<>();
        reports.add(new Report());
        when(reportRepository.findAll()).thenReturn(reports);
        when(genericMapper.mapList(anyList(), eq(ReportDto.class))).thenReturn(new ArrayList<>());

        // When
        List<ReportDto> retrievedReports = reportService.getAllReports();

        // Then
        assertEquals(1, retrievedReports.size());
    }

    @Test
    void testGetReportsByUserId() {
        // Given
        User user = new User();
        user.setId(1L);
        List<Report> reports = new ArrayList<>();
        reports.add(new Report());
        user.setReports(reports);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(genericMapper.mapList(anyList(), eq(ReportDto.class))).thenReturn(new ArrayList<>());

        // When
        List<ReportDto> retrievedReports = reportService.getReportsByUserId(1L);

        // Then
        assertEquals(1, retrievedReports.size());
    }

    @Test
    void testGetReportsByPostId() {
        // Given
        Post post = new Post();
        post.setId(1L);
        List<Report> reports = new ArrayList<>();
        reports.add(new Report());
        post.setReports(reports);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(genericMapper.mapList(anyList(), eq(ReportDto.class))).thenReturn(new ArrayList<>());

        // When
        List<ReportDto> retrievedReports = reportService.getReportsByPostId(1L);

        // Then
        assertEquals(1, retrievedReports.size());
    }
}
