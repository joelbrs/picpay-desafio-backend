package br.com.joelf.picpay.infraestructure.dataproviders;

import br.com.joelf.picpay.application.dataprovider.PublishNotificationDataProvider;
import br.com.joelf.picpay.application.dataprovider.exceptions.PublishNotificationDataProviderException;
import br.com.joelf.picpay.domain.entities.Transfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class PublishNotificationDataProviderImpl implements PublishNotificationDataProvider {

    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Transfer transfer) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(transfer));
        } catch (AmqpException | JsonProcessingException e) {
            throw new PublishNotificationDataProviderException(e.getMessage());
        }
    }
}
