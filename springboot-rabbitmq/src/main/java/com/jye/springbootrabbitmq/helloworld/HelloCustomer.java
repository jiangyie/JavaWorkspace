package com.jye.springbootrabbitmq.helloworld;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 15:01
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloCustomer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("message: " + message);
    }
}
