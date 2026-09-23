package com.laressa.notification.event;

import com.laressa.notification.domain.NotificationLog;
import com.laressa.notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionEventListenerTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private TransactionEventListener transactionEventListener;

    @Test
    void shouldPersistNotificationWhenEventIsHandled() {
        UUID transactionId = UUID.randomUUID();
        TransactionEvent event = new TransactionEvent(
                transactionId,
                "DEPOSITO",
                UUID.randomUUID(),
                null,
                new BigDecimal("100.00"),
                "CONCLUIDA"
        );

        transactionEventListener.handle(event);

        ArgumentCaptor<NotificationLog> notificationCaptor = ArgumentCaptor.forClass(NotificationLog.class);
        verify(notificationRepository).save(notificationCaptor.capture());

        NotificationLog savedLog = notificationCaptor.getValue();
        assertThat(savedLog.getTransactionId()).isEqualTo(transactionId);
        assertThat(savedLog.getMessage()).contains("DEPOSITO", "100.00", "CONCLUIDA");
    }
}