package br.com.via.backoffice.integration.transactions.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Transaction(@JsonProperty("id") String id,
                          @JsonProperty("user") UserDto user,
                          @JsonProperty("value") BigDecimal value,
                          @JsonProperty("description") String description,
                          @JsonProperty("transaction_date") String date) { }
