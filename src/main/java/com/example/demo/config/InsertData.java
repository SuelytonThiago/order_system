package com.example.demo.config;

import com.example.demo.entities.Products;
import com.example.demo.resources.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class InsertData implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public InsertData(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var product1 = new Products(null, "caderno", 10.99, 10);
        var product2 = new Products(null, "panela", 35.99, 10);
        var product3 = new Products(null, "liquidificador", 89.99, 10);

        productRepository.saveAll(Arrays.asList(product1,product2,product3));
    }
}
