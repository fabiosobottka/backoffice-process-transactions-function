package br.com.via.backoffice.integration.transactions.infra.api;

import br.com.via.backoffice.integration.transactions.infra.api.dto.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionUpdateRequest(@JsonProperty("transaction_status") TransactionStatus status) {
}
