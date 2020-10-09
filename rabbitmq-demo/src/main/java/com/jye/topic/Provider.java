package com.jye.topic;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 11:22
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String routingKey = "user.test.com.jye";
        channel.basicPublish("topics", routingKey, null, "这是topic发布模型".getBytes());
        RabbitMQUtil.close(channel, connection);
    }
}
