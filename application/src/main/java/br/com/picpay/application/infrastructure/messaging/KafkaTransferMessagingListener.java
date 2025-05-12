package br.com.picpay.application.infrastructure.messaging;

import br.com.picpay.domain.Transfer;
import br.com.picpay.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaTransferMessagingListener {

    private final TransferService transferService;

    @KafkaListener(
        topics = "${messaging.topics.transfer.name}",
        groupId = "${messaging.topics.transfer.group-id}"
    )
    public void listener(Transfer transfer) {
        transferService.proccess(transfer);
    }
}
