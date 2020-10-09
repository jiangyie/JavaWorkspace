package com.jye.topic;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 11:25
 */
public class Customer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "topics", "user.*.#");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者：" + new String(body));
            }
        });
    }
}
