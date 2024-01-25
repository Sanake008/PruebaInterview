package com.prueba.Prueba.service;

import com.prueba.Prueba.dto.OrderDTO;
import com.prueba.Prueba.dto.OrderDetailDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;

import java.util.List;

public interface OrderDetailService {
    public OrderDetailDTO save(OrderDetailDTO order)throws BadRequestException;
    public OrderDetailDTO updateWhole(OrderDetailDTO orderDTO) throws BadRequestException;
    public OrderDetailDTO getOrderDetail(Integer id) throws NotFoundException;
    public List<OrderDetailDTO> getAll();
    public void delete(Integer id)throws NotFoundException;
}
