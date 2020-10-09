package com.jye.test;

import com.jye.springbootrabbitmq.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 14:50
 */
@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //1.helloWorld
    @Test
    public void testHelloWorld(){
        rabbitTemplate.convertAndSend("hello", "hello world!");
    }

    //2.work
    @Test
    public void testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", i + "===>work模型");
        }
    }

    //3.fanout
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("fanouts", "", "fanout广播模型");
    }

    //4.routing
    @Test
    public void testRouting(){
        rabbitTemplate.convertAndSend("directs", "error", "routing模型");
    }

    //5.topic
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("springboot.topics", "user.test", "topic模型");
    }
}
