package com.jye.routing;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 11:00
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        channel.exchangeDeclare(exchangeName, "direct");
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue, exchangeName, "warning");
        channel.queueBind(queue, exchangeName, "info");

        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
            }
        });
    }
}
