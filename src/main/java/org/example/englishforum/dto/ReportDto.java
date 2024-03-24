package org.example.englishforum.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.englishforum.entity.Post;
import org.example.englishforum.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Long id;
    private String content  ;
    private UserDto user;
}
