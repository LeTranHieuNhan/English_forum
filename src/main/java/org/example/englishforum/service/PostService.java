package org.example.englishforum.service;

import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.response.PostResponse;
import org.example.englishforum.entity.Post;

import java.util.List;

public interface PostService {
    PostResponse findAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto findPostById(Long id);

    PostDto createPost(PostDto newPost, long userId, long categoryId);

    void deletePost(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    List<PostDto> searchPosts(String keyword);

    void deleteAllPosts();

}