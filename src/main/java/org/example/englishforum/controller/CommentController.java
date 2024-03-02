package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.CommentDto;
import org.example.englishforum.dto.PostDto;
import org.example.englishforum.entity.Comment;
import org.example.englishforum.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{postId}")
    ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) throws Exception {
        return new ResponseEntity<>(commentService.findCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findCommentById(id), HttpStatus.OK);
    }

    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto newComment, @PathVariable Long postId,@PathVariable Long userId) throws Exception {
        return new ResponseEntity<>(commentService.createComment(newComment, postId, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto CommentDto) throws Exception {
        return new ResponseEntity<>(commentService.updateComment(id, CommentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
