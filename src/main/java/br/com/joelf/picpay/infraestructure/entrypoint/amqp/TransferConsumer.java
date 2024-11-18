package br.com.joelf.picpay.infraestructure.entrypoint.amqp;

import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.ProcessTransferUseCase;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransferConsumer {

    private final ProcessTransferUseCase processTransferUseCase;

    @RabbitListener(queues = "${amqp.queues.transfer.name}")
    public void consume(@Payload Transfer transfer) {
        processTransferUseCase.execute(transfer);
    }
}
