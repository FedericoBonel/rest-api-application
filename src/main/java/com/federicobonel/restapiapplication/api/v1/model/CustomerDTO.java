package com.federicobonel.restapiapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String name;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
