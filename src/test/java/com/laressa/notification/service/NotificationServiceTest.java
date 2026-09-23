package com.laressa.notification.service;

import com.laressa.notification.domain.NotificationChannel;
import com.laressa.notification.domain.NotificationLog;
import com.laressa.notification.exception.NotificationNotFoundException;
import com.laressa.notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldFindNotificationByIdSuccessfully() {
        UUID notificationId = UUID.randomUUID();

        NotificationLog log = new NotificationLog(
                UUID.randomUUID(),
                "Transação DEPOSITO no valor de R$100.00 foi processada com o status CONCLUIDA",
                NotificationChannel.EMAIL
        );
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(log));

        NotificationLog result = notificationService.findById(notificationId);

        assertThat(result).isEqualTo(log);
    }

    @Test
    void shouldThrowExceptionWhenNotificationNotFound() {
        UUID notificationId = UUID.randomUUID();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> notificationService.findById(notificationId))
                .isInstanceOf(NotificationNotFoundException.class);
    }

    @Test
    void shouldListNotifications() {

        NotificationLog log = new NotificationLog(
                UUID.randomUUID(),
                "Transação SAQUE no valor de R$50.00 foi processada com o status CONCLUIDA",
                NotificationChannel.EMAIL
        );
        when(notificationRepository.findAll()).thenReturn(List.of(log));

        List<NotificationLog> result = notificationService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(log);
    }
}