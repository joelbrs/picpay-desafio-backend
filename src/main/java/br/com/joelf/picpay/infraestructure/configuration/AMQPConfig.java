package br.com.joelf.picpay.infraestructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Value("${amqp.queues.transfer.name}")
    private String transferQueue;

    @Value("${amqp.queues.notification.name}")
    private String notificationQueue;

    @Bean("transferQueue")
    public Queue transferQueue() {
        return new Queue(transferQueue, Boolean.TRUE);
    }

    @Bean("notificationQueue")
    public Queue notificationQueue() {
        return new Queue(notificationQueue, Boolean.TRUE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
