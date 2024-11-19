package br.com.joelf.picpay.infraestructure.entrypoint.amqp;

import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.ProcessTransferUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransferConsumer {

    private final ProcessTransferUseCase processTransferUseCase;

    @RabbitListener(queues = "${amqp.queues.transfer.name}")
    public void consume(@Payload String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Transfer transfer = mapper.readValue(payload, Transfer.class);

        processTransferUseCase.execute(transfer);
    }
}
