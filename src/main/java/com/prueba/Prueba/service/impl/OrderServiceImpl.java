package com.prueba.Prueba.service.impl;

import com.prueba.Prueba.dto.OrderDTO;
import com.prueba.Prueba.dto.ProductDTO;
import com.prueba.Prueba.entity.Orders;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.repository.OrderRepository;
import com.prueba.Prueba.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) throws BadRequestException {
        log.info("Saving new Order from DTO: {}", orderDTO.toString());
        Optional<Orders> optionalOrder = orderRepository.findById(orderDTO.getId());
        if(optionalOrder.isPresent()){
            log.error("Order already exists!!");
            throw new BadRequestException("Order already exists");
        }
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        orderRepository.save(orders);
        log.info("OrderSaved: {}", orders.toString());
        return  orderDTO;
    }

    @Override
    public OrderDTO updateWhole(OrderDTO orderDTO) throws BadRequestException {
        log.info("Saving new Order from DTO: {}", orderDTO.toString());
        Optional<Orders> optionalOrder = orderRepository.findById(orderDTO.getId());
        if(optionalOrder.isEmpty()){
            log.error("Order Not Found!!");
            throw new BadRequestException("Order Not found!!");
        }
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        orderRepository.save(orders);
        return  orderDTO;
    }


    @Override
    public OrderDTO getOrder(Integer id) throws NotFoundException {
        Optional<Orders> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            log.error("Order Not Found!!");
            throw new NotFoundException("Order NotFound");
        }
        OrderDTO orderDTO = modelMapper.map(optionalOrder.get(), OrderDTO.class);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAll() {
        log.info("*********************");
        return orderRepository.findAll().stream().map(o->OrderDTO.builder()
                .id(o.getId())
                .clientId(o.getClientId())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Integer id) throws NotFoundException {
        Orders optionalOrders = orderRepository.findById(id).orElseThrow(()->{
            try {
                throw new NotFoundException("Order Not Found");
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        orderRepository.delete(optionalOrders);
    }
}
