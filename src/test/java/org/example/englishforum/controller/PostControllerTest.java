package org.example.englishforum.controller;

import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.response.PostResponse;
import org.example.englishforum.service.PostService;
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

class PostControllerTest {

    @Mock
    PostService postService;

    @InjectMocks
    PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all posts and return ok status")
    void getAllPosts() {
        PostResponse postResponse = new PostResponse();
        when(postService.findAllPosts(0, 10, "id", "ASC")).thenReturn(postResponse);

        ResponseEntity<PostResponse> response = postController.getAllPosts(0, 10, "id", "ASC");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postResponse, response.getBody());
    }

    @Test
    @DisplayName("Should get post by id and return ok status")
    void getPostById() {
        PostDto postDto = new PostDto();
        when(postService.findPostById(1L)).thenReturn(postDto);

        ResponseEntity<PostDto> response = postController.getPostById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDto, response.getBody());
    }

    @Test
    @DisplayName("Should create post and return ok status")
    void createPost() {
        PostDto postDto = new PostDto();
        when(postService.createPost(postDto, 1L, 1L)).thenReturn(postDto);

        ResponseEntity<PostDto> response = postController.createPost(postDto, 1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDto, response.getBody());
    }

    @Test
    @DisplayName("Should update post and return ok status")
    void updatePost() {
        PostDto postDto = new PostDto();
        when(postService.updatePost(1L, postDto)).thenReturn(postDto);

        ResponseEntity<PostDto> response = postController.updatePost(1L, postDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDto, response.getBody());
    }

    @Test
    @DisplayName("Should delete post and return no content status")
    void deletePost() {
        doNothing().when(postService).deletePost(1L);

        postController.deletePost(1L);

        verify(postService).deletePost(1L);
    }

    @Test
    @DisplayName("Should search posts and return list of posts")
    void searchPosts() {
        List<PostDto> postDtoList = Collections.singletonList(new PostDto());
        when(postService.searchPosts("keyword")).thenReturn(postDtoList);

        List<PostDto> response = postController.searchPosts("keyword");

        assertEquals(postDtoList, response);
    }
}
