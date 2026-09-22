package com.laressa.notification.controller;

import com.laressa.notification.domain.NotificationLog;
import com.laressa.notification.dto.NotificationResponseDTO;
import com.laressa.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> findById(@PathVariable UUID id) {

        NotificationLog log = notificationService.findById(id);
        return ResponseEntity.ok(NotificationResponseDTO.fromEntity(log));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> findAll() {

        List<NotificationResponseDTO> notifications = notificationService.findAll()
                .stream()
                .map(NotificationResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(notifications);
    }
}