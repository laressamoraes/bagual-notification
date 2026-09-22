package com.laressa.notification.dto;

import com.laressa.notification.domain.NotificationChannel;
import com.laressa.notification.domain.NotificationLog;

import java.util.UUID;

public record NotificationResponseDTO(

        UUID notificationId,
        UUID transactionId,
        String message,
        NotificationChannel channel
) {
    public static NotificationResponseDTO fromEntity(NotificationLog notificationLog) {
        return new NotificationResponseDTO(
                notificationLog.getNotificationId(),
                notificationLog.getTransactionId(),
                notificationLog.getMessage(),
                notificationLog.getChannel()
        );
    }

}