package org.j2os.messaging.consumer;

import org.j2os.domain.model.Order;
import org.j2os.service.OrderProcessingService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderQueueConsumer {

    private final OrderProcessingService orderProcessingService;

    public OrderQueueConsumer(OrderProcessingService orderProcessingService){
        this.orderProcessingService = orderProcessingService;
    }

    @JmsListener(
            destination = "order.queue",
            containerFactory = "queueListenerFactory"
    )
    public void consume(Order order){
        System.out.println("Processing order: " + order.orderId());
//        orderProcessingService.process(order);
    }


//    @JmsListener(destination = "order.queue")
//    public void processOrder(Order order) throws InterruptedException {
//        System.out.println("Processing order: " + order.orderId());
//        Thread.sleep(3000); // شبیه‌سازی پردازش سنگین
//        System.out.println("Order processed");
//    }
//
//    @JmsListener(destination = "order.queue")
//    public void sendEmail(Order order) {
//        System.out.println("Sending email for order: " + order.orderId());
//    }
//
//    @JmsListener(destination = "order.queue")
//    public void auditOrder(Order order) {
//        System.out.println("Auditing order: " + order.orderId());
//    }


}
