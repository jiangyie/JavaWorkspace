package com.jye.springbootrabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 15:11
 */
@Component
public class WorkCustomer {

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive1(String message){
        System.out.println("message-1 :" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void receive2(String message){
        System.out.println("message-2 :" + message);
    }
}
