package com.jye.routing;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/29 10:54
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        channel.exchangeDeclare(exchangeName, "direct");
        String routingKey = "warning";
        channel.basicPublish(exchangeName, routingKey, null, ("这是:" + routingKey).getBytes());
        RabbitMQUtil.close(channel, connection);

    }
}
