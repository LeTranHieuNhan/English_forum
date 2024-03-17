package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.CategoryDto;
import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.dto.response.PostResponse;
import org.example.englishforum.entity.Category;
import org.example.englishforum.entity.Comment;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.CategoryRepository;
import org.example.englishforum.repository.CommentRepository;
import org.example.englishforum.repository.PostRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.PostService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final GenericMapper genericMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public PostResponse findAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostDto> postDtos = genericMapper.mapList(posts.getContent(), PostDto.class);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto findPostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            return genericMapper.map(postOptional.get(), PostDto.class);
        } else {
            throw new RuntimeException("Post does not exist");
        }
    }

    @Transactional
    @Override
    public PostDto createPost(PostDto newPostDto, long userId, long categoryId) {
        newPostDto.setTime_created(new Date());

        Post newPostEntity = genericMapper.map(newPostDto, Post.class);
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category does not exist");
        }


        User user = userOptional.get();
        Category category = categoryOptional.get();

        newPostEntity.setUser(user);
        newPostEntity.setCategory(category);

        Post savedPostEntity = postRepository.save(newPostEntity);

        PostDto savedPostDto = genericMapper.map(savedPostEntity, PostDto.class);
        savedPostDto.setUser(genericMapper.map(user, UserDto.class));


        return savedPostDto;
    }


    @Transactional
    @Override
    public void deletePost(Long id) {
        boolean isExist = postRepository.existsById(id);


        if (isExist) {
            Post post = postRepository.findById(id).get();
            commentRepository.deleteAll(post.getComments());

            postRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post existingPost = postOptional.get();

            // Update fields
            existingPost.setViews(postDto.getViews());
            existingPost.setText(postDto.getText());
            existingPost.setPost_background_img(postDto.getPost_background_img());
            existingPost.setTitle(postDto.getTitle());
            existingPost.setTime_updated(new Date());

            // Save the updated post
            Post savedPost = postRepository.save(existingPost);

            // Map and return the updated post
            return genericMapper.map(savedPost, PostDto.class);
        } else {
            throw new RuntimeException("Post with id " + id + " not found");
        }
    }
}

