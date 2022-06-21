package com.federicobonel.restapiapplication.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String lastname;
    private String customerUrl;
}