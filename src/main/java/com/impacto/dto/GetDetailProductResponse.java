package com.impacto.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class GetDetailProductResponse {

    private Integer id;
    private String code;
    private String name;
    private Integer price;
    private Integer categoryId;
    private String categoryDesc;

}
