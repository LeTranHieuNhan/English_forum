package org.example.englishforum.controller;

import org.example.englishforum.dto.ReplyCommentDto;
import org.example.englishforum.service.ReplyCommentService;
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

class ReplyCommentControllerTest {

    @Mock
    ReplyCommentService replyCommentService;

    @InjectMocks
    ReplyCommentController replyCommentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save reply comment and return created status")
    void saveReplyComment() {
        ReplyCommentDto replyCommentDto = new ReplyCommentDto();
        when(replyCommentService.saveReplyComment(replyCommentDto, 1L, 1L)).thenReturn(replyCommentDto);

        ResponseEntity<ReplyCommentDto> response = replyCommentController.saveReplyComment(replyCommentDto, 1L, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(replyCommentDto, response.getBody());
    }

    @Test
    @DisplayName("Should get all reply comments by comment id and return ok status")
    void getReplyCommentsByCommentId() {
        List<ReplyCommentDto> replyCommentDtoList = Collections.singletonList(new ReplyCommentDto());
        when(replyCommentService.getReplyCommentsByCommentId(1L)).thenReturn(replyCommentDtoList);

        ResponseEntity<List<ReplyCommentDto>> response = replyCommentController.getReplyCommentsByCommentId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(replyCommentDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should delete reply comment and return no content status")
    void deleteReplyComment() {
        doNothing().when(replyCommentService).deleteReplyComment(1L);

        ResponseEntity<Void> response = replyCommentController.deleteReplyComment(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Should get reply comment by id and return ok status")
    void getReplyCommentById() {
        ReplyCommentDto replyCommentDto = new ReplyCommentDto();
        when(replyCommentService.getReplyCommentById(1L)).thenReturn(replyCommentDto);

        ResponseEntity<ReplyCommentDto> response = replyCommentController.getReplyCommentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(replyCommentDto, response.getBody());
    }

    @Test
    @DisplayName("Should update reply comment and return ok status")
    void updateReplyComment() {
        ReplyCommentDto replyCommentDto = new ReplyCommentDto();
        when(replyCommentService.updateReplyComment(replyCommentDto, 1L)).thenReturn(replyCommentDto);

        ResponseEntity<ReplyCommentDto> response = replyCommentController.updateReplyComment(1L, replyCommentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(replyCommentDto, response.getBody());
    }
}
