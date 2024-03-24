package org.example.englishforum.service;

import org.example.englishforum.dto.ReplyCommentDto;
import org.example.englishforum.entity.ReplyComment;

import java.util.List;

public interface ReplyCommentService {
    public ReplyCommentDto saveReplyComment(ReplyCommentDto replyComment , Long commentId , Long userId);

    public List<ReplyCommentDto> getReplyCommentsByCommentId(Long commentId);

    public void deleteReplyComment(Long replyCommentId);

    public ReplyCommentDto getReplyCommentById(Long replyCommentId);

    public ReplyCommentDto updateReplyComment(ReplyCommentDto replyComment , Long id);
}
