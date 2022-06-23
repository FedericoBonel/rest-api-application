package com.federicobonel.restapiapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    @ApiModelProperty(value = "Name of the vendor shop", example = "Walmart Av. avenue 123")
    String name;

    @JsonProperty("vendor_url")
    String vendorUrl;
}
