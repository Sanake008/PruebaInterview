package com.prueba.Prueba.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DBData {
    private List<ClientDTO> client;
    private List<ProductDTO> product;
    private List<OrderDTO> orders;
    private List<OrderDetailDTO> orderDetail;
}
