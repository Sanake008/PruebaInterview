package com.prueba.Prueba.service.impl;

import com.prueba.Prueba.dto.ProductDTO;
import com.prueba.Prueba.entity.Product;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.repository.ProductRepository;
import com.prueba.Prueba.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) throws BadRequestException {
        log.info("Saving product DTO: {}", productDTO.toString());
        Optional<Product> productDTOOptional = productRepository.findById(productDTO.getId());
        if(productDTOOptional.isPresent()){
            throw new BadRequestException("Product already exists!!");
        }
        Product product = modelMapper.map(productDTO, Product.class);
        log.info("Saving product: {}", product.toString());
        productRepository.save(product);
        return productDTO;
    }

    @Override
    public ProductDTO updateWhole(ProductDTO productDTO) throws BadRequestException {
        Optional<Product> productDTOOptional = productRepository.findById(productDTO.getId());
        if(productDTOOptional.isEmpty()){
            throw new BadRequestException("Product doesn't exists!!");
        }
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
        return productDTO;
    }


    @Override
    public ProductDTO getProduct(Integer id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(p->ProductDTO.builder()
                .id(p.getId())
                .productName(p.getProductName())
                .price(p.getPrice())
                .description(p.getDescription())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
