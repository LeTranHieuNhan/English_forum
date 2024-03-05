package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.ReactionDto;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.Reaction;
import org.example.englishforum.entity.ReactionType;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.PostRepository;
import org.example.englishforum.repository.ReactionRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.ReactionService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final GenericMapper genericMapper;
    private final ReactionRepository reactionRepository;

    @Override
    @Transactional
    public ReactionDto react(Long userId, Long postId, ReactionType reactionType) {
        Optional<User> foundUser = userRepository.findById(userId);
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty() || foundUser.isEmpty())
            throw new RuntimeException("User or Post id does not exit");

        Reaction reaction = new Reaction();
        reaction.setReactionType(reactionType);
        reaction.setPost(foundPost.get());
        reaction.setUser(foundUser.get());

        Reaction savedReaction = reactionRepository.save(reaction);
        foundUser.get().getReactions().add(savedReaction);
        foundPost.get().getReactions().add(savedReaction);

        return genericMapper.map(savedReaction, ReactionDto.class);
    }

    @Override
    public List<ReactionDto> getAllReactionsByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post does not exit"));
        List<Reaction> reactions = post.getReactions();

        return genericMapper.mapList(reactions, ReactionDto.class);
    }

    @Override
    public void unReaction(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User does not exist"));

        Reaction reactionToRemove = null;
        for (Reaction reaction : post.getReactions()) {
            if (reaction.getUser().getId().equals(userId)) {
                reactionToRemove = reaction;
                break;
            }
        }

        if (reactionToRemove != null) {
            post.getReactions().remove(reactionToRemove);
            user.getReactions().remove(reactionToRemove);
            reactionRepository.delete(reactionToRemove);
        }

    }

    @Override
    public void unReaction(Long id) {
        Reaction reaction = reactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Reaction does not exist"));
        reaction.getPost().getReactions().remove(reaction);
        reaction.getUser().getReactions().remove(reaction);
        reactionRepository.delete(reaction);
    }
}
