package com.jye.helloWorld;

import com.jye.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yangen Jiang
 * @created 2020/9/28 16:11
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
        channel.queueDeclare("hello", true, false, false, null);
        channel.basicPublish("", "hello", null, "hello helloWorld".getBytes());
        RabbitMQUtil.close(channel, connection);
    }
}
