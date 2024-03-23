package org.example.englishforum.repository;

import org.example.englishforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<User>> findByUsername(String userName);

   Optional< User> findByEmail(String userEmail);
}
