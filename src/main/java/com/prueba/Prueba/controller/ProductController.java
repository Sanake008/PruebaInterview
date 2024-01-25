package com.prueba.Prueba.controller;

import com.prueba.Prueba.dto.ProductDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.service.impl.ProductServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }




    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user')")
    public ProductDTO create(@RequestBody ProductDTO productDTO) throws BadRequestException {
        return productService.save(productDTO);
    }

    @PutMapping("/update")
    public ProductDTO update(@RequestBody ProductDTO productDTO) throws BadRequestException {
        return productService.updateWhole(productDTO);
    }

    @GetMapping("/get")
    public ProductDTO getClientById(@RequestParam(name = "id") Integer id) throws NotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping("/getAll")
    public List<ProductDTO> getAll(){
        return  productService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam (name = "id") Integer id) throws NotFoundException {
        productService.delete(id);
    }
    
}
