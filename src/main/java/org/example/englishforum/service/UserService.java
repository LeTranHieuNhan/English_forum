package org.example.englishforum.service;

import com.cloudinary.Cloudinary;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto findUserById(Long postId);

    UserDto createUser(UserDto userDTO, MultipartFile multipartFile
) throws IOException;
    void deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
}
