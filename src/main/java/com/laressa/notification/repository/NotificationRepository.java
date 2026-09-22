package com.laressa.notification.repository;

import com.laressa.notification.domain.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationLog, UUID> {
}