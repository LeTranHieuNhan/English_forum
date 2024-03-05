package org.example.englishforum.service;

import org.example.englishforum.dto.ReactionDto;
import org.example.englishforum.entity.Reaction;
import org.example.englishforum.entity.ReactionType;

import java.util.List;

public interface ReactionService {
    ReactionDto react(Long userId , Long postId, ReactionType reactionType);

    List<ReactionDto> getAllReactionsByPostId(Long postId);

    void unReaction(Long postId, Long userId);

    void unReaction(Long id);



}
