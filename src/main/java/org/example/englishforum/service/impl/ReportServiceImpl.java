package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.ReportDto;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.Report;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.PostRepository;
import org.example.englishforum.repository.ReportRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.ReactionService;
import org.example.englishforum.service.ReportService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final GenericMapper genericMapper;
@Transactional
    @Override
    public ReportDto saveReport(ReportDto reportDto, Long userId, Long postId) {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Report report = genericMapper.map(reportDto, Report.class);
        report.setUser(user);
        report.setPost(post);

    System.out.println("reportDto.getContent() = " + report.getContent());

    Report save = reportRepository.save(report);
    return genericMapper.map(save, ReportDto.class);
    }

    @Override
    public ReportDto getReportById(Long id) {

        return genericMapper.map(reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found")), ReportDto.class);
    }

    @Override
    public ReportDto updateReport(ReportDto reportDto, Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setContent(reportDto.getContent());
        return genericMapper.map(reportRepository.save(report), ReportDto.class);
    }

    @Override
    public void deleteReport(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        Post post = report.getPost();
        post.getReports().remove(report);
        postRepository.save(post);
        User user = report.getUser();
        user.getReports().remove(report);
        userRepository.save(user);
        reportRepository.deleteById(id);
    }

    @Override
    public List<ReportDto> getAllReports() {
        return genericMapper.mapList(reportRepository.findAll(), ReportDto.class);
    }

    @Override
    public List<ReportDto> getReportsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return genericMapper.mapList(user.getReports(), ReportDto.class);

    }

    @Override
    public List<ReportDto> getReportsByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return genericMapper.mapList(post.getReports(), ReportDto.class);
    }
}
