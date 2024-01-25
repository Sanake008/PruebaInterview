package com.prueba.Prueba.service;

import com.prueba.Prueba.dto.OrderDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;

import java.util.List;

public interface OrderService {
    public OrderDTO save(OrderDTO order)throws BadRequestException;
    public OrderDTO updateWhole(OrderDTO orderDTO) throws BadRequestException;
    public OrderDTO getOrder(Integer costumer) throws NotFoundException;
    public List<OrderDTO> getAll();
    public void deleteOrder(Integer id)throws NotFoundException;


}
