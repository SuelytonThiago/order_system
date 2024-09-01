package com.example.demo.rabbitmq;

import com.example.demo.entities.Orders;
import com.example.demo.sevices.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final OrderService orderService;

    @RabbitListener(queues = Constants.QUEUE_CHANGE_ORDER_STATUS)
    public void changeStatus(Long id){
        orderService.setOrderStatus(id);
    }
}
