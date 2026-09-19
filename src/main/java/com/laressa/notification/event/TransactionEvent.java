package com.laressa.notification.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionEvent(

    UUID transactionId,
    String transactionType,
    UUID originAccountId,
    UUID destinationAccountId,
    BigDecimal amount,
    String transactionStatus
) {

    @JsonCreator
    public TransactionEvent(
            @JsonProperty("transactionId") UUID transactionId,
            @JsonProperty("transactionType") String transactionType,
            @JsonProperty("originAccountId") UUID originAccountId,
            @JsonProperty("destinationAccountId") UUID destinationAccountId,
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("transactionStatus") String transactionStatus
    ) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
    }
}