package org.example.englishforum.repository;

import org.example.englishforum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.text LIKE %:keyword%")
    List<Post> searchByTitleOrText(@Param("keyword") String keyword);
}
