package com.prueba.Prueba.controller;

import com.prueba.Prueba.dto.ClientDTO;
import com.prueba.Prueba.dto.OrderDTO;
import com.prueba.Prueba.dto.ProductDTO;
import com.prueba.Prueba.entity.AuthRequest;
import com.prueba.Prueba.entity.Client;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.service.impl.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    OrderDetailServiceImpl orderDetailService;
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Client client) {
        return service.addUser(client);
    }

    @GetMapping("/user/getAll")
    @PreAuthorize("hasAuthority('user')")
    public List<ClientDTO> getAllClients() {
        return clientService.getAll();
    }

    @PostMapping("/order/create")
    @PreAuthorize("hasAuthority('user')")
    public OrderDTO createOrder(@RequestBody OrderDTO request) throws BadRequestException {
        log.info("Creating orders");
        return orderService.save(request);
    }

    @PutMapping("/order/updateWhole")
    @PreAuthorize("hasAuthority('user')")
    public OrderDTO updateOrder(@RequestBody OrderDTO request) throws BadRequestException {
        return orderService.updateWhole(request);
    }

    @GetMapping("/order/getByCustomer")
    @PreAuthorize("hasAuthority('user')")
    public OrderDTO getByCostumer(@RequestParam(name = "id") Integer id) throws NotFoundException {
        return orderService.getOrder(id);
    }

    @GetMapping("/order/getAll")
    @PreAuthorize("hasAuthority('user')")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAll();
    }

    @DeleteMapping("/order/delete")
    @PreAuthorize("hasAuthority('user')")
    public void deleteOrder(@RequestParam Integer id) throws NotFoundException {
        orderService.deleteOrder(id);
    }

    @PostMapping("/product/create")
    @PreAuthorize("hasAuthority('user')")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) throws BadRequestException {
        return productService.save(productDTO);
    }

    @PutMapping("/product/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) throws BadRequestException {
        return productService.updateWhole(productDTO);
    }

    @GetMapping("/product/get")
    public ProductDTO getProductById(@RequestParam(name = "id") Integer id) throws NotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping("/product/getAll")
    public List<ProductDTO> getAllProducts(){
        return  productService.getAll();
    }

    @DeleteMapping("/product/delete")
    public void deleteProduct(@RequestParam (name = "id") Integer id) throws NotFoundException {
        productService.delete(id);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}