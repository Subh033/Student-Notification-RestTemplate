package com.example.NotificationService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document("notifications")
public class Notification {
    @Id
    private String id;
    private String message;

}
