package com.example.demo.controllers;

import com.example.demo.dto.RequestOrderDto;
import com.example.demo.entities.Orders;
import com.example.demo.sevices.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@Valid @RequestBody RequestOrderDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewOrder(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(service.getOrder(id));
    }
}
