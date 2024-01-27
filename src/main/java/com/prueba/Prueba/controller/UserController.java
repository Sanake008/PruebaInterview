package com.prueba.Prueba.controller;

import com.prueba.Prueba.dto.ClientDTO;
import com.prueba.Prueba.dto.DBData;
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
    private JwtService jwtService;

    @Autowired
    PopulationDBServiceImpl populationDBService;
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

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/populateDb")
    public String populateDB(@RequestBody DBData dbData) throws BadRequestException {
        log.info("Starting process!!");
        return populationDBService.populate(dbData);
    }

}