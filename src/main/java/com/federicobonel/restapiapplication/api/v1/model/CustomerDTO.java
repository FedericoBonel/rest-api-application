package com.federicobonel.restapiapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @ApiModelProperty(value = "First name of the customer", example = "Jasmine")
    private String name;
    @ApiModelProperty(value = "Last name of the customer", example = "Ottonello")
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
