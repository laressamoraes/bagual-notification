package com.laressa.notification.service;

import com.laressa.notification.domain.NotificationLog;
import com.laressa.notification.exception.NotificationNotFoundException;
import com.laressa.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationLog findById(UUID notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notificação não encontrada " + notificationId));
    }

    public List<NotificationLog> findAll() {
        return notificationRepository.findAll();
    }
}