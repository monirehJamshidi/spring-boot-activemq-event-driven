package org.j2os.service;

import org.j2os.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService {

    public void process(Order order) {
        System.out.println("Processing order: " + order.orderId());
        // simulate heavy processing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Order processed successfully");
    }
}
