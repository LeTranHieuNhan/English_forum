package org.example.englishforum.controller;

import org.example.englishforum.dto.CommentDto;
import org.example.englishforum.service.CommentService;
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

class CommentControllerTest {

    @Mock
    CommentService commentService;

    @InjectMocks
    CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all comments by post id and return ok status")
    void getCommentsByPostId() throws Exception {
        List<CommentDto> commentDtoList = Collections.singletonList(new CommentDto());
        when(commentService.findCommentsByPostId(1L)).thenReturn(commentDtoList);

        ResponseEntity<List<CommentDto>> response = commentController.getCommentsByPostId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should get comment by id and return ok status")
    void getCommentById() {
        CommentDto commentDto = new CommentDto();
        when(commentService.findCommentById(1L)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.getCommentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
    }

    @Test
    @DisplayName("Should create comment and return created status")
    void createComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        when(commentService.createComment(commentDto, 1L, 1L)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.createComment(commentDto, 1L, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
    }

    @Test
    @DisplayName("Should update comment and return ok status")
    void updateComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        when(commentService.updateComment(1L, commentDto)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.updateComment(1L, commentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
    }

    @Test
    @DisplayName("Should delete comment and return no content status")
    void deleteComment() {
        doNothing().when(commentService).deleteComment(1L);

        ResponseEntity<Void> response = commentController.deleteComment(1L);
        verify(commentService).deleteComment(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
