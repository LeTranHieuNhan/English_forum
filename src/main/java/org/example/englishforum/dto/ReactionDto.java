package org.example.englishforum.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.englishforum.entity.ReactionType;
import org.example.englishforum.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDto {
    private int id;
    @Enumerated(EnumType.STRING)

    private ReactionType reactionType;
    private UserDto user;

}
