package com.example.demo.rabbitmq;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Connections {

    private static final String NAME_EXCHANGE = "amq.direct";

    private final AmqpAdmin amqpAdmin;

    private Queue queue(String nameQueue){
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return  new Binding(
                queue.getName(),
                Binding.DestinationType.QUEUE,
                exchange.getName(),
                queue.getName(),
                null
        );
    }

    @PostConstruct
    private void add(){
        Queue changeStatus = this.queue(Constants.QUEUE_CHANGE_ORDER_STATUS);
        DirectExchange exchange = this.directExchange();

        Binding bindingChangeStatus = this.binding(changeStatus, exchange);

        this.amqpAdmin.declareQueue(changeStatus);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(bindingChangeStatus);
    }
}
