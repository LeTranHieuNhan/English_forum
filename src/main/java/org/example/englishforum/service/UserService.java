package org.example.englishforum.service;

import org.example.englishforum.dto.UserDto;
import org.example.englishforum.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto findUserById(Long postId);

    UserDto createUser(UserDto userDTO);
    void deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
}
