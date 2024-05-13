package com.example.demo_ibm_mq.Configuration;

import com.ibm.mq.jms.MQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class MQConfiguration {
	@Value("${project.mq.host}")
    private String host;
    @Value("${project.mq.port}")
    private Integer port;
    @Value("${project.mq.queue-manager}")
    private String queueManager;
    @Value("${project.mq.channel}")
    private String channel;
    @Value("${project.mq.receive-timeout}")
    private long receiveTimeout;

    @Value("${project.mq.username}")
    private String username;

    @Value("${project.mq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() throws JMSException {
        MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
        mqConnectionFactory.setHostName(host);
        mqConnectionFactory.setPort(port);
        mqConnectionFactory.setQueueManager(queueManager);
        mqConnectionFactory.setChannel(channel);
        mqConnectionFactory.setCCSID(1208);
        mqConnectionFactory.setStringProperty(WMQConstants.USERID, username);
        mqConnectionFactory.setStringProperty(WMQConstants.PASSWORD, password);
        try (Connection connection = mqConnectionFactory.createConnection("Hushal", "12345")) {
            System.out.println("Connection successful!");
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return mqConnectionFactory;
    }
    
    @Bean
    @Primary
    public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);
        cachingConnectionFactory.setSessionCacheSize(500);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }
    
    @Bean
    public JmsOperations jmsOperations(CachingConnectionFactory cachingConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
        jmsTemplate.setReceiveTimeout(receiveTimeout);
        return jmsTemplate;
    }
}
