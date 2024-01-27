package com.prueba.Prueba.service.impl;

import com.prueba.Prueba.dto.DBData;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.service.PopulationDBService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class PopulationDBServiceImpl implements PopulationDBService {

    private final ClientServiceImpl clientService;
    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;
    private final OrderDetailServiceImpl orderDetailService;

    public PopulationDBServiceImpl(ClientServiceImpl clientService, ProductServiceImpl productService, OrderServiceImpl orderService, OrderDetailServiceImpl orderDetailService) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public String populate(DBData dbData) throws BadRequestException {
        log.info("Populate client table");
        dbData.getClient().stream().forEach(c-> {
            try {
                clientService.save(c);
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Populate product table");
        dbData.getProduct().stream().forEach(p-> {
            try {
                productService.save(p);
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Populate order table");
        dbData.getOrders().stream().forEach(o-> {
            try {
                orderService.save(o);
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Populate order detail table");
        dbData.getOrderDetail().stream().forEach(od-> {
            try {
                log.info("firts step");
                orderDetailService.save(od);
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });


        return "Data was written successfully!!";
    }
}
