package br.com.picpay.application.infrastructure.messaging;

import br.com.picpay.domain.Transfer;
import br.com.picpay.ports.TransferMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class KafkaTransferMessaging implements TransferMessaging {

    @Value(value = "${messaging.topics.transfer.name}")
    private String topicName;

    private final KafkaTemplate<String, Transfer> kafkaTemplate;

    @Override
    public void send(Transfer transfer) {
        kafkaTemplate.send(topicName, transfer);
    }
}
