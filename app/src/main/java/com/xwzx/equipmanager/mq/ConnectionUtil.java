package com.xwzx.equipmanager.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xwzx.equipmanager.Constant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {


    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Constant.BASE_MQ_URL);
        connectionFactory.setPort(5672);
//        connectionFactory.setPort(5672);
//        ExecutorService service = Executors.newFixedThreadPool(20);
//        connectionFactory.setSharedExecutor(service);
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        connectionFactory.setTopologyRecoveryEnabled(true);
        connectionFactory.setUsername("boreas");
        connectionFactory.setPassword("123456");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }

}
