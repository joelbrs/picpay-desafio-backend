package br.com.joelf.picpay.infraestructure.dataproviders;

import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.application.dataprovider.exceptions.PublishTransferDataProviderException;
import br.com.joelf.picpay.domain.entities.Transfer;
import lombok.AllArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class PublishTransferDataProviderImpl implements PublishTransferDataProvider {

    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Transfer transfer) throws PublishTransferDataProviderException {
        try {
            rabbitTemplate.convertAndSend(queue.getName(), transfer);
        } catch (AmqpException e) {
            throw new PublishTransferDataProviderException(e.getMessage());
        }
    }
}
