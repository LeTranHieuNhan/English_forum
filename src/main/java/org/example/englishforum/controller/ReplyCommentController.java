package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.ReplyCommentDto;
import org.example.englishforum.service.ReplyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replycomment")
@RequiredArgsConstructor
public class ReplyCommentController {
    private final ReplyCommentService replyCommentService;



    @PostMapping("/{commentId}/{userId}")
    public ResponseEntity<ReplyCommentDto> saveReplyComment(@RequestBody ReplyCommentDto replyComment,
                                                            @PathVariable("commentId") Long commentId,
                                                            @PathVariable("userId") Long userId) {
        ReplyCommentDto savedReplyComment = replyCommentService.saveReplyComment(replyComment, commentId, userId);
        return new ResponseEntity<>(savedReplyComment, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<List<ReplyCommentDto>> getReplyCommentsByCommentId(@PathVariable("commentId") Long commentId) {
        List<ReplyCommentDto> replyComments = replyCommentService.getReplyCommentsByCommentId(commentId);
        return new ResponseEntity<>(replyComments, HttpStatus.OK);
    }

    @DeleteMapping("/{replyCommentId}")
    public ResponseEntity<Void> deleteReplyComment(@PathVariable("replyCommentId") Long replyCommentId) {
        replyCommentService.deleteReplyComment(replyCommentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{replyCommentId}")
    public ResponseEntity<ReplyCommentDto> getReplyCommentById(@PathVariable("replyCommentId") Long replyCommentId) {
        ReplyCommentDto replyComment = replyCommentService.getReplyCommentById(replyCommentId);
        return new ResponseEntity<>(replyComment, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReplyCommentDto> updateReplyComment(@RequestBody ReplyCommentDto replyComment) {
        ReplyCommentDto updatedReplyComment = replyCommentService.updateReplyComment(replyComment);
        return new ResponseEntity<>(updatedReplyComment, HttpStatus.OK);
    }

}
