package org.j2os.service;

import lombok.extern.slf4j.Slf4j;
import org.j2os.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProcessingService {

    public void process(Order order) {
        log.info("Processing order: " + order.orderId());
        // simulate heavy processing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("process Exception " + e.getMessage());
        }

        log.info("Order processed successfully");
    }
}
