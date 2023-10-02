package br.com.via.backoffice.integration.transactions.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionEvent(@JsonProperty("operation") String operation,
                               @JsonProperty("transaction") Transaction transaction) {


    public String getTransactionId() {
        return this.transaction.id();
    }

}
