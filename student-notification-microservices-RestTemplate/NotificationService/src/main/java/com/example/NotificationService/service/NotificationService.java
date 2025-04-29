package com.example.NotificationService.service;

import com.example.NotificationService.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface NotificationService {
    Notification saveMessage(String message);

    Notification saveNotification(Map<String, String> request);

    List<Notification> getAllNotifications();

    String deleteNotification(String id);

    String updateNotification(String id, String message);
}
