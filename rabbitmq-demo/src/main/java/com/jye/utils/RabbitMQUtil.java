package com.jye.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Yangen Jiang
 * @created 2020/9/28 15:58
 */
public class RabbitMQUtil {

    public static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.13.126");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("jye");
        connectionFactory.setVirtualHost("/ems");
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
