package com.federicobonel.restapiapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {

    @ApiModelProperty(value = "Name of the category", example = "Fruits")
    private String name;

    @JsonProperty("category_url")
    private String categoryUrl;
}
