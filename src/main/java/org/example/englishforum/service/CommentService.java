package org.example.englishforum.service;

import org.example.englishforum.dto.CommentDto;
import org.example.englishforum.entity.Comment;
import org.example.englishforum.dto.PostDto;

import java.util.List;

public interface CommentService {

    CommentDto findCommentById(Long id);

    List<CommentDto> findCommentsByPostId(Long id) throws Exception;

    CommentDto createComment(CommentDto newComment, Long postId, Long userId) throws Exception;

    void deleteComment(Long id);

    CommentDto updateComment(Long id, CommentDto commentDto) throws Exception;
}
