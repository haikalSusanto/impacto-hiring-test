package com.impacto.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class UpdateProductRequest {

    private String code;

    private String name;

    @Min(value = 0, message = "price must be positive number")
    private Integer price;

    @Min(value = 1, message = "categoryId must be above zero")
    private Integer categoryId;

}