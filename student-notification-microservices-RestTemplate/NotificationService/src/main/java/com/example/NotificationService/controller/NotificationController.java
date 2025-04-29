package com.example.NotificationService.controller;

import com.example.NotificationService.entity.Notification;
import com.example.NotificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/rM")
    public ResponseEntity<String> recieveMessage(@RequestBody String message)
    {
        notificationService.saveMessage(message);
        return ResponseEntity.ok("Notification saved successfully!");
    }
    @PostMapping("/recieveMessage")
    public ResponseEntity<String> recieveMessage(@RequestBody Map<String,String> mp)
    {
        notificationService.saveNotification(mp);
        return ResponseEntity.ok("Notification saved successfully!");
    }

    @GetMapping("/all")
    public List<Notification> getNotifications() {
        return notificationService.getAllNotifications();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNotification(@PathVariable String id) {
        return notificationService.deleteNotification(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateNotification(@PathVariable String id, @RequestBody String message) {
        String result = notificationService.updateNotification(id, message);
        return ResponseEntity.ok(result);
    }
}
