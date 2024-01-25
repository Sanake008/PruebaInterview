package com.prueba.Prueba.dto;

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
    private Integer id;
    private List<Product> productId;
    private Orders orderId;
    private Integer quantity;
}
