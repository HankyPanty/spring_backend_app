package com.locoProject.backendApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDto {
    @JsonProperty("type")
    public String type;

    @JsonProperty("amount")
    public Long amount;

    @JsonProperty("parent_id")
    public Long parentId;
}
