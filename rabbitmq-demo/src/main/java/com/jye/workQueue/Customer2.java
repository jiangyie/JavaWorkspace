package com.jye.workQueue;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/28 16:55
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        final Channel channel = connection.createChannel();
        //一次只接受一条未确认消息
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
                //手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
