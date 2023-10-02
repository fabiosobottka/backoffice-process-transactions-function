package br.com.via.backoffice.integration.transactions.event.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(@JsonProperty("registration_number") Long registrationNumber,
                      @JsonProperty("name") String name) { }
