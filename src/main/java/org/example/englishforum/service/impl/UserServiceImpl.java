package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.UserService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenericMapper genericMapper;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return genericMapper.mapList(users, UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new RuntimeException("User does not exist");
        }

        return genericMapper.map(user.get(), UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(genericMapper.map(newUser, User.class));
        return genericMapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        boolean isExist = userRepository.existsById(id);

        if (isExist) {
            userRepository.deleteById(id);
        } else
            throw new RuntimeException("User doesnt exit with ID " + id);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {

            user.get().setUsername(userDto.getUsername());
            user.get().setEmail(userDto.getEmail());
            user.get().setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.get().setAvatar(userDto.getAvatar());
            user.get().setBan(false);

            return genericMapper.map(user, UserDto.class);
        } else
            throw new RuntimeException(" User not found with id " + id);
    }
}
