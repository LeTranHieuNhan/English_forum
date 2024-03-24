package org.example.englishforum.service.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.ReplyCommentDto;
import org.example.englishforum.entity.Comment;
import org.example.englishforum.entity.ReplyComment;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.CommentRepository;
import org.example.englishforum.repository.ReplyCommentRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.ReplyCommentService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyCommentServiceImpl implements ReplyCommentService {
    private final ReplyCommentRepository replyCommentRepository;
    private final GenericMapper genericMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public ReplyCommentDto saveReplyComment(ReplyCommentDto replyComment, Long commentId, Long userId) {
        replyComment.setTime_created(new Date());
        ReplyComment replyCommentEntity = genericMapper.map(replyComment, ReplyComment.class);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment does not exist"));
        replyCommentEntity.setComment(comment);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User does not exist"));
        replyCommentEntity.setUser(user);
        return genericMapper.map(replyCommentRepository.save(replyCommentEntity), ReplyCommentDto.class);
    }

    @Override
    public List<ReplyCommentDto> getReplyCommentsByCommentId(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment does not exist"));
        List<ReplyComment> replyComments = comment.getReplyComments();
        return genericMapper.mapList(replyComments, ReplyCommentDto.class);
    }

    @Override
    public void deleteReplyComment(Long replyCommentId) {
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId).orElseThrow(() -> new RuntimeException("Reply Comment does not exist"));
        replyCommentRepository.delete(replyComment);

    }

    @Override
    public ReplyCommentDto getReplyCommentById(Long replyCommentId) {
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId).orElseThrow(() -> new RuntimeException("Reply Comment does not exist"));


        return genericMapper.map(replyComment, ReplyCommentDto.class);
    }

    @Override
    public ReplyCommentDto updateReplyComment(ReplyCommentDto replyComment, Long id) {
        Comment comment = commentRepository.findById(replyCommentRepository.findById(id).orElseThrow(() -> new RuntimeException("Reply Comment does not exist")).getComment().getId()).orElseThrow(() -> new RuntimeException("Comment does not exist"));

        // Map the DTO to the entity
        ReplyComment replyCommentEntity = replyCommentRepository.findById(id).orElseThrow(() -> new RuntimeException("Reply Comment does not exist"));
        replyCommentEntity.setBody(replyComment.getBody());

        // Set the associated Comment entity
        replyCommentEntity.setComment(comment);

        // Save the ReplyComment entity
        replyCommentEntity.setTime_updated(new Date());
        ReplyComment updatedReplyComment = replyCommentRepository.save(replyCommentEntity);

        // Map the updated entity back to DTO
        return genericMapper.map(updatedReplyComment, ReplyCommentDto.class);
    }
}
