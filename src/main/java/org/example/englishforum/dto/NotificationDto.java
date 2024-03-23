package org.example.englishforum.dto;

public class NotificationDto {
    private String id;
    private String content;
    private String sender;
    private String recipient;
    private String notificationType;
    private boolean delivered;
    private boolean read;
    private UserDto fromUser;
    private UserDto toUser;
}
