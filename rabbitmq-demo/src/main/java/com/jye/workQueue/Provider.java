package com.jye.workQueue;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/28 16:50
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "work", null, (i + "===>>hello work queue").getBytes());
        }
        RabbitMQUtil.close(channel, connection);
    }
}
