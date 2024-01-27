package com.prueba.Prueba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prueba.Prueba.entity.Orders;
import com.prueba.Prueba.entity.Product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("productId")
    private List<Product> productId;
    @JsonProperty("orderId")
    private Orders orderId;
    @JsonProperty("quantity")
    private Integer quantity;
}
