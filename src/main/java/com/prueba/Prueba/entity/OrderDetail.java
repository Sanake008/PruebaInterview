package com.prueba.Prueba.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="order_detail")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Orders.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Orders orderId;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private List<Product> productId;
    @Column(name = "quantity")
    private Integer quantity;

}
