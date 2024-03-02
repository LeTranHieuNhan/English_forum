package org.example.englishforum.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.englishforum.entity.User;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")  // This establishes the Many-to-One relationship
    private Post post;
}

