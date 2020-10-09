package com.jye.springbootrabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 16:05
 */
@Component
public class TopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    key = {"user.#"},
                    exchange = @Exchange(name = "springboot.topics", type = "topic")
            )
    })
    public void receive(String message){
        System.out.println("message: " + message);
    }
}
