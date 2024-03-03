package org.example.englishforum.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.PostDto;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.User;
import org.example.englishforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @GetMapping("")
    List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping()
        public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        return new ResponseEntity<>(userService.createUser(newUser, multipartFile), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {


        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
