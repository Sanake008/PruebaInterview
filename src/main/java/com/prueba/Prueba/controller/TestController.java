package com.prueba.Prueba.controller;


import com.prueba.Prueba.dto.OrderDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Log4j2
public class TestController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderDTO request) throws BadRequestException {
        log.info("Creating orders");
        return orderService.save(request);
    }

    @PutMapping("/updateWhole")
    public OrderDTO update(@RequestBody OrderDTO request) throws BadRequestException {
        return orderService.updateWhole(request);
    }

    @GetMapping("/getByCustomer")
    public OrderDTO getByCostumer(@RequestParam(name = "id") Integer id) throws NotFoundException {
        return orderService.getOrder(id);
    }

    @GetMapping("/getAll")
    public List<OrderDTO> getAll(){

        log.info("Getting all********");
        List<OrderDTO> orderDTOList = orderService.getAll();
        log.info("This is my information: {}", orderDTOList.size());
        return orderService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        orderService.deleteOrder(id);
    }


}
