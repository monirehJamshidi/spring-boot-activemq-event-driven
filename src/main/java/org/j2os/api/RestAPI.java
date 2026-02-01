package org.j2os.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPI {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public String send() {
        jmsTemplate.convertAndSend("x", "Monireh");
        return "ok";
    }

    @JmsListener(destination = "x")
    public void messageListener1(String message){
        System.out.println(message);
    }
}
