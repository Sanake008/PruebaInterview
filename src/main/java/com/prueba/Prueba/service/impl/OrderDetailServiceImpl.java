package com.prueba.Prueba.service.impl;

import com.prueba.Prueba.dto.OrderDetailDTO;
import com.prueba.Prueba.entity.Detail;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.repository.OrderDetailRepository;
import com.prueba.Prueba.service.OrderDetailService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OrderDetailServiceImpl implements OrderDetailService {


    private final OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailDTO save(OrderDetailDTO order) throws BadRequestException {
        log.info("Saving new OrderDetail: {}", order.toString());
        /*Optional<Detail> optionalOrderDetail = orderDetailRepository.findById(order.getId());
        if(optionalOrderDetail.isPresent()){
            throw new BadRequestException("Order Already Exists");
        }*/
        log.info("Hearrrrrrrrr");
        Detail orderDetail = modelMapper.map(order, Detail.class);
        log.info("Saving new OrderDetail: {}", orderDetail.toString());
        orderDetailRepository.save(orderDetail);
        log.info("Order Detail Saved");

        return order;
    }

    @Override
    public OrderDetailDTO updateWhole(OrderDetailDTO orderDTO) throws BadRequestException {
        Optional<Detail> optionalOrderDetail = orderDetailRepository.findById(orderDTO.getId());
        if(optionalOrderDetail.isEmpty()){
            throw new BadRequestException("Order detail doesn't exist!!");
        }
        Detail orderDetail = modelMapper.map(orderDTO,Detail.class);
        orderDetailRepository.save(orderDetail);
        return orderDTO;
    }

    @Override
    public OrderDetailDTO getOrderDetail(Integer id) throws NotFoundException {
        Detail orderDetail = orderDetailRepository.findById(id).orElseThrow(()->new NotFoundException("Order detail not found"));
        OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetail, OrderDetailDTO.class);
        return orderDetailDTO;
    }

    @Override
    public List<Detail> getAll() {
        log.info("Retreiving all order detail!!");
        List<Detail> orderDetail = orderDetailRepository.findAll();
        orderDetail.stream().forEach(od->log.info(od.toString()));
        log.info(orderDetail.size()+"----------------------------------");
        return orderDetailRepository.findAll();/*.stream().map(od-> OrderDetailDTO.builder()
                .orderId(od.getOrderId())
                .id(od.getId())
                .productId(od.getProductId())
                .quantity(od.getQuantity())
                .build()).collect(Collectors.toList());*/
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Detail orderDetail = orderDetailRepository.findById(id).orElseThrow(()->new NotFoundException("Order detail not found"));
        orderDetailRepository.delete(orderDetail);
    }
}
