package com.prueba.Prueba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private Double price;
}
