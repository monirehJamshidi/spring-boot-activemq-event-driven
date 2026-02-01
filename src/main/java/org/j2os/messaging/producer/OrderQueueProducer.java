package org.j2os.messaging.producer;


import org.j2os.domain.model.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderQueueProducer {

    private static final String ORDER_QUEUE = "order.queue";

    private final JmsTemplate jmsTemplate;

    public OrderQueueProducer(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void send(Order order) {
        jmsTemplate.convertAndSend(ORDER_QUEUE, order);/*, message -> {
            message.setStringProperty("type", "ORDER_PROCESS");

            return message;
        });*/
    }

}
