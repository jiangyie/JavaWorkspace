package com.jye.fanout;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 9:55
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs", "fanout");
        channel.basicPublish("logs", "", null, "hello world".getBytes());
        RabbitMQUtil.close(channel, connection);

    }
}
