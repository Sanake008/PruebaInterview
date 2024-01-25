package com.prueba.Prueba.service;

import com.prueba.Prueba.dto.ProductDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public ProductDTO save(ProductDTO productDTO)throws BadRequestException;
    public ProductDTO updateWhole(ProductDTO productDTO) throws BadRequestException;
    public ProductDTO getProduct(Integer id) throws NotFoundException;
    public List<ProductDTO> getAll();
    public void delete(Integer id)throws NotFoundException;
}
