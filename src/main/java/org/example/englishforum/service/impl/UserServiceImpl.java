package org.example.englishforum.service.impl;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.RoleDto;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.entity.Role;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.RoleRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.UserService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenericMapper genericMapper;
    private final Cloudinary cloudinary;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return genericMapper.mapList(users, UserDto.class);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username ));
            }
        };
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
    @Transactional
    public UserDto createUser(UserDto newUser) throws IOException {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role does not exit "));
        User user = genericMapper.map(newUser, User.class);

        // Ensure that the role field is initialized
        if (user.getRole() == null) {
            user.setRole(new Role());
        }

        user.setRole(role);

        User savedUser = userRepository.save(user);
        UserDto map = genericMapper.map(savedUser, UserDto.class);
        map.setRole(genericMapper.map(role, RoleDto.class));

        return map;
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
