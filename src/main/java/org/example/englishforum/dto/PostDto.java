package org.example.englishforum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.englishforum.entity.Comment;
import org.example.englishforum.entity.Reaction;
import org.example.englishforum.entity.User;


import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String text;
    private Date time_created;
    private Date time_updated;
    private String post_background_img;
    private Long views;

    private UserDto user;
    private List<CommentDto> comments;
    private List<ReactionDto> reactions;
    private CategoryDto category;
}
