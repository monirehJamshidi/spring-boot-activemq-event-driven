package org.j2os.config;


import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;


//@Configuration
//@EnableJms
//public class JmsConfig {
//
//    @Bean
//    public DefaultJmsListenerContainerFactory queueListenerFactory(
//            ConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory factory =
//                new DefaultJmsListenerContainerFactory();
//
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPubSubDomain(false); // QUEUE
//        factory.setConcurrency("1-5");
//
//        return factory;
//    }
//
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter =
//                new MappingJackson2MessageConverter();
//
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//
//        return converter;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(
//            ConnectionFactory connectionFactory,
//            MessageConverter jacksonJmsMessageConverter) {
//
//        JmsTemplate template = new JmsTemplate(connectionFactory);
//        template.setMessageConverter(jacksonJmsMessageConverter);
//        template.setPubSubDomain(false);
//        return template;
//    }
//
//
//}



@Configuration
@EnableJms
public class JmsConfig {

    // 1️⃣ Converter برای JSON
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT); // JSON -> TextMessage
        converter.setTypeIdPropertyName("_type"); // Type header
        return converter;
    }

    // 2️⃣ Listener Factory برای Queue
    @Bean
    public DefaultJmsListenerContainerFactory queueListenerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter jacksonJmsMessageConverter) {

        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonJmsMessageConverter); // ✅ Converter اضافه شد
        factory.setPubSubDomain(false); // Queue
        factory.setConcurrency("1-5"); // چند Thread مصرف‌کننده
        return factory;
    }

    // 3️⃣ JmsTemplate با converter
    @Bean
    public JmsTemplate jmsTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter jacksonJmsMessageConverter) {

        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setMessageConverter(jacksonJmsMessageConverter); // ✅ Converter اضافه شد
        template.setPubSubDomain(false); // Queue
        return template;
    }
}
