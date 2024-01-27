package com.prueba.Prueba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="detail")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Orders.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Orders orderId;
    @JsonBackReference
    @OneToMany(mappedBy = "detail",fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Product> productId;
    @Column(name = "quantity")
    private Integer quantity;

}
