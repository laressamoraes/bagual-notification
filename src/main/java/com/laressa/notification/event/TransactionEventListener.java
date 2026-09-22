package com.laressa.notification.event;

import com.laressa.notification.domain.NotificationChannel;
import com.laressa.notification.domain.NotificationLog;
import com.laressa.notification.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventListener {

    private final NotificationRepository notificationRepository;

    public TransactionEventListener(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "transacoes", groupId = "notification-group")
    public void handle(TransactionEvent event) {

        String message = buildMessage(event);

        NotificationLog log = new NotificationLog(
                event.transactionId(),
                message,
                NotificationChannel.EMAIL
        );

        notificationRepository.save(log);
    }

    private String buildMessage(TransactionEvent event) {
        return "Transação " + event.transactionType() + " no valor de R$"
                + event.amount() + " foi processada com o status " + event.transactionStatus();
    }
}