package com.example.demo.sevices;

import com.example.demo.entities.Products;
import com.example.demo.exceptios.ObjectNotFoundException;
import com.example.demo.resources.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Products findById( Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(("this product is not found"))
        );
    }

    public List<Products> findAll(){
        return productRepository.findAll();
    }

}
