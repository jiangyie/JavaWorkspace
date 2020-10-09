package com.jye.springbootrabbitmq.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 15:52
 */
@Component
public class RoutingCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    key = {"info", "error"},
                    exchange = @Exchange(value = "directs", type = "direct")
            )
    })
    public void receive1(String messsage){
        System.out.println("message1 :" + messsage);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    key = {"test", "error"},
                    exchange = @Exchange(value = "directs", type = "direct")
            )
    })
    public void receive2(String messsage){
        System.out.println("message1 :" + messsage);
    }
}
