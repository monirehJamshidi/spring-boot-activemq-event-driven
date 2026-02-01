package org.j2os.api;

import lombok.extern.slf4j.Slf4j;
import org.j2os.domain.model.Order;
import org.j2os.messaging.producer.OrderQueueProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    private final OrderQueueProducer producer;

    public OrderController(OrderQueueProducer producer){
        this.producer = producer;
    }

    @PostMapping("/order")
    public String createOrder(){
        log.info("createOrder");
        Order order = new Order("ORDER-1", "Labtop",2);
        producer.send(order);

        return "Order submitted for processing";
    }
}
