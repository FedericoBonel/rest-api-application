package com.federicobonel.restapiapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDTO {

    private String name;

    @JsonProperty("category_url")
    private String categoryUrl;
}
