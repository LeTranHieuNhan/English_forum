package org.example.englishforum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentDto {
    private Long id;
    private String body;
    private Date time_created;
    private Date time_updated;
    private UserDto user;
}
