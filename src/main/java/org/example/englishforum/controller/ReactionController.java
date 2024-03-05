package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.ReactionDto;
import org.example.englishforum.entity.ReactionType;
import org.example.englishforum.service.ReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reactions")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;

    @PostMapping("")
    public ResponseEntity<ReactionDto> react(@RequestParam Long userId,
                                             @RequestParam Long postId,
                                             @RequestParam ReactionType reactionType) {
        ReactionDto reactionDto = reactionService.react(userId, postId, reactionType);
        return new ResponseEntity<>(reactionDto, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<ReactionDto>> getAllReactionsByPostId(@PathVariable Long postId) {
        List<ReactionDto> reactionDtos = reactionService.getAllReactionsByPostId(postId);
        return new ResponseEntity<>(reactionDtos, HttpStatus.OK);
    }

    @DeleteMapping("/unreact")
    public ResponseEntity<String> unReaction(@RequestParam Long postId,
                                             @RequestParam Long userId) {
        reactionService.unReaction(postId, userId);
        return ResponseEntity.ok("Reaction removed successfully.");
    }

    @DeleteMapping("/unreact/{id}")
    public ResponseEntity<String> unReactionById(@PathVariable Long id) {
        reactionService.unReaction(id);
        return ResponseEntity.ok("Reaction removed successfully.");
    }
}