package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.CommentDto;
import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.entity.Comment;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.CommentRepository;
import org.example.englishforum.repository.PostRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.CommentService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final GenericMapper genericMapper;

    @Override
    public CommentDto findCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isEmpty()) {
            throw new RuntimeException("Comment does not exist");
        }

        CommentDto commentDto = genericMapper.map(comment.get(), CommentDto.class);

        // Set UserDto
        UserDto userDto = genericMapper.map(comment.get().getUser(), UserDto.class);
        commentDto.setUserDto(userDto);



        return commentDto;
    }

    @Override
    public List<CommentDto> findCommentsByPostId(Long postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty())
            throw new Exception("Post does not exist");

        List<Comment> comments = post.get().getComments();
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = genericMapper.map(comment, CommentDto.class);

            // Set UserDto
            UserDto userDto = genericMapper.map(comment.getUser(), UserDto.class);
            commentDto.setUserDto(userDto);



            commentDtos.add(commentDto);
        }

        return commentDtos;
    }

    @Override
    @Transactional
    public CommentDto createComment(CommentDto newComment, Long postId, Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty() || post.isEmpty()) {
            throw new Exception("Post or User does not exist");
        }

        Comment comment = genericMapper.map(newComment, Comment.class);

        // Set ID to null to ensure a new comment is created
        comment.setId(null);

        comment.setUser(user.get());
        comment.setPost(post.get());

        CommentDto createdCommentDto = genericMapper.map(commentRepository.save(comment), CommentDto.class);

        // Set UserDto
        UserDto userDto = genericMapper.map(user.get(), UserDto.class);
        createdCommentDto.setUserDto(userDto);



        return createdCommentDto;
    }

    @Override
    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            throw new Exception("Comment not found");
        }

        Comment comment = optionalComment.get();
        comment.setBody(commentDto.getBody());

        CommentDto updatedCommentDto = genericMapper.map(comment, CommentDto.class);

        // Set UserDto
        UserDto userDto = genericMapper.map(comment.getUser(), UserDto.class);
        updatedCommentDto.setUserDto(userDto);

        // Set PostDto


        return updatedCommentDto;
    }
}

