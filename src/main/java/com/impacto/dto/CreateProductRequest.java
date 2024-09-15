package com.impacto.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CreateProductRequest {

    @NotEmpty(message = "code is required")
    private String code;

    @NotEmpty(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be positive number")
    private Integer price;

    @NotNull(message = "categoryId is required")
    @Min(value = 1, message = "categoryId must be above zero")
    private Integer categoryId;

}
