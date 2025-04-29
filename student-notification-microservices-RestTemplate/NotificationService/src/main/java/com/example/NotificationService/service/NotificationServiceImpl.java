package com.example.NotificationService.service;

import com.example.NotificationService.entity.Notification;
import com.example.NotificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification saveMessage(String message)
    {
        Notification notification=new Notification();
        notification.setMessage(message);
        notificationRepository.save(notification);
        return notificationRepository.save(notification);
    }
    @Override
    public Notification saveNotification(Map<String, String> request)
    {
        Notification notification=new Notification();
        notification.setId(request.get("id"));
        notification.setMessage(request.get("message"));
        notificationRepository.save(notification);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public String deleteNotification(String id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return "Notification with ID " + id + " deleted successfully.";
        } else {
            return "Notification with ID " + id + " not found.";
        }
    }
    @Override
    public String updateNotification(String id, String message) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setMessage(message);
            notificationRepository.save(notification);
            return "Notification updated successfully";
        } else {
            throw new RuntimeException("Notification not found with ID: " + id);
        }
    }
}
