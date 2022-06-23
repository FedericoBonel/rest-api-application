package com.federicobonel.restapiapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VendorDTO {

    String name;

    @JsonProperty("vendor_url")
    String vendorUrl;
}
