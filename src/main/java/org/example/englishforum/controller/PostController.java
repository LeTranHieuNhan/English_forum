package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.response.PostResponse;
import org.example.englishforum.entity.Post;
import org.example.englishforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    ResponseEntity<PostResponse> getAllPosts(@RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "ASC") String sortDir) {
        PostResponse productResponse = postService.findAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findPostById(id), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable long userId, @RequestParam long categoryId) {
        return new ResponseEntity<>(postService.createPost(postDto, userId, categoryId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
    @GetMapping("/search")
    public List<PostDto> searchPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }

    @DeleteMapping("/all")
    public void deleteAllPosts() {
        postService.deleteAllPosts();
    }

}