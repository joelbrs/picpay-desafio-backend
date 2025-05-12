package br.com.picpay.application.config;

import br.com.picpay.application.infrastructure.messaging.KafkaTransferMessaging;
import br.com.picpay.domain.Transfer;
import br.com.picpay.ports.TransferMessaging;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

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

    @Value("${messaging.topics.transfer.group-id}")
    private String groupIdTransferTopic;

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

    @Bean
    public ConsumerFactory<String, Transfer> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdTransferTopic);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "br.com.picpay.domain");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "br.com.picpay.domain.Transfer");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Transfer>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Transfer> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ProducerFactory<String, Transfer> producerFactoryTransfer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Transfer> kafkaTemplateTransfer() {
        return new KafkaTemplate<>(producerFactoryTransfer());
    }

    @Bean
    public TransferMessaging transferMessaging(
        KafkaTemplate<String, Transfer> kafkaTemplate
    )  {
        return new KafkaTransferMessaging(kafkaTemplate);
    }
}
