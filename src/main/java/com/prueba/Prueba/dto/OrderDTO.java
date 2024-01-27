package com.prueba.Prueba.dto;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.prueba.Prueba.entity.Client;
import com.prueba.Prueba.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("clientId")
    private Client clientId;
    @JsonProperty("amount")
    private Double amount;
}
