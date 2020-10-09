package com.jye.fanout;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 10:02
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("logs", "fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //将临时队列绑定交换机
        channel.queueBind(queue, "logs", "");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });

    }
}
