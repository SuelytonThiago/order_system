package com.example.demo.sevices;

import com.example.demo.dto.RequestOrderDto;
import com.example.demo.entities.Orders;
import com.example.demo.enums.OrderStatus;
import com.example.demo.exceptios.CustomException;
import com.example.demo.exceptios.ObjectNotFoundException;
import com.example.demo.rabbitmq.Constants;
import com.example.demo.resources.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final RabbitMqService rabbitService;

    @Transactional
    public String createNewOrder(RequestOrderDto dto){
        var product = productService.findById(dto.getProductId());
        var order = new Orders();

        order.setUsername(dto.getUsername());
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.WAITING_CONFIRMATION);
        orderRepository.save(order);

        order.setProduct(product);
        order = orderRepository.save(order);

        this.rabbitService.sendMessage(Constants.QUEUE_CHANGE_ORDER_STATUS,order.getId());

        return "your order has been requested successfully, use this id to track the order status:" + order.getId().toString() + order.getStatus().toString();
    }

    public Orders getOrder(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(() -> new ObjectNotFoundException("This order is not found"));
    }

    public void setOrderStatus(Long id){
        var order = getOrder(id);
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }
}
