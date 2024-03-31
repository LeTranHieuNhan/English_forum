package org.example.englishforum.controller;

import org.example.englishforum.dto.ReactionDto;
import org.example.englishforum.entity.ReactionType;
import org.example.englishforum.service.ReactionService;
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

class ReactionControllerTest {

    @Mock
    ReactionService reactionService;

    @InjectMocks
    ReactionController reactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should react and return created status")
    void react() {
        ReactionDto reactionDto = new ReactionDto();
        when(reactionService.react(1L, 1L, ReactionType.LIKE)).thenReturn(reactionDto);

        ResponseEntity<ReactionDto> response = reactionController.react(1L, 1L, ReactionType.LIKE);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reactionDto, response.getBody());
    }

    @Test
    @DisplayName("Should get all reactions by post id and return ok status")
    void getAllReactionsByPostId() {
        List<ReactionDto> reactionDtoList = Collections.singletonList(new ReactionDto());
        when(reactionService.getAllReactionsByPostId(1L)).thenReturn(reactionDtoList);

        ResponseEntity<List<ReactionDto>> response = reactionController.getAllReactionsByPostId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reactionDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should unreact by post id and user id and return ok status")
    void unReaction() {
        doNothing().when(reactionService).unReaction(1L, 1L);

        ResponseEntity<String> response = reactionController.unReaction(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reaction removed successfully.", response.getBody());
    }

    @Test
    @DisplayName("Should unreact by id and return ok status")
    void unReactionById() {
        doNothing().when(reactionService).unReaction(1L);

        ResponseEntity<String> response = reactionController.unReactionById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reaction removed successfully.", response.getBody());
    }
}
