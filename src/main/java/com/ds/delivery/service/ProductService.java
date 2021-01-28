package com.ds.delivery.service;

import com.ds.delivery.dto.ProductDto;
import com.ds.delivery.entities.Product;
import com.ds.delivery.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAllByOrderByNameAsc();
        return modelMapper.map(productList, new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    public ProductDto entityToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product dtoToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

}
