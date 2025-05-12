package br.com.picpay.application.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${messaging.topics.transfer.name}")
    private String transferTopicName;

    @Value(value = "${messaging.topics.transfer.num-partitions}")
    private Integer numPartitionsTransferTopic;

    @Value(value = "${messaging.topics.transfer.replication-factor}")
    private Short replicationFactorTransferTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic transferTopic() {
        return new NewTopic(transferTopicName, numPartitionsTransferTopic, replicationFactorTransferTopic);
    }
}
