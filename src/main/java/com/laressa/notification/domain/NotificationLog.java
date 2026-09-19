package com.laressa.notification.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "notifications")
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID notificationId;

    @Column(nullable = false)
    private UUID transactionId;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationChannel channel;

    public NotificationLog() {
    }

    public NotificationLog(UUID transactionId, String message, NotificationChannel channel) {
        this.transactionId = transactionId;
        this.message = message;
        this.channel = channel;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }

    public NotificationChannel getChannel() {
        return channel;
    }
}