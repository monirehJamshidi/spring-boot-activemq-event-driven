package org.j2os.api;

import org.j2os.domain.model.Order;
import org.j2os.messaging.producer.OrderQueueProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderQueueProducer producer;

    public OrderController(OrderQueueProducer producer){
        this.producer = producer;
    }

    @PostMapping("/order")
    public String createOrder(){
        Order order = new Order("ORDER-1", "Labtop",2);
        producer.send(order);

        return "Order submitted for processing";
    }
}
