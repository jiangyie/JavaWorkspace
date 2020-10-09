package com.jye.springbootrabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 15:27
 */
@Component
public class FanoutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "fanouts", type = "fanout"))
    })
    public void receive1(String message){
        System.out.println("message-1 :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "fanouts", type = "fanout"))
    })
    public void receive2(String message){
        System.out.println("message-2 :" + message);
    }
}
