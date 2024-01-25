package com.prueba.Prueba.controller;

import com.prueba.Prueba.dto.OrderDetailDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.service.impl.OrderDetailServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OderDetailController {
    
    private final OrderDetailServiceImpl orderDetailService;

    public OderDetailController(OrderDetailServiceImpl orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/create")
    public OrderDetailDTO create(@RequestBody OrderDetailDTO orderDetailDTO) throws BadRequestException {
        return orderDetailService.save(orderDetailDTO);
    }
    @PutMapping("/update")
    public OrderDetailDTO update(@RequestBody OrderDetailDTO orderDetailDTO) throws BadRequestException {
        return orderDetailService.updateWhole(orderDetailDTO);
    }
    @GetMapping("/get")
    public OrderDetailDTO getClientById(@RequestParam (name = "id") Integer id) throws NotFoundException {
        return orderDetailService.getOrderDetail(id);
    }
    @GetMapping("/getAll")

    public List<OrderDetailDTO> getAll(){
        return  orderDetailService.getAll();
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam (name = "id") Integer id) throws NotFoundException {
        orderDetailService.delete(id);
    }
    
}
