package br.com.joelf.picpay.infraestructure.entrypoint.amqp;

import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.SendNotificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NotificationConsumer {

    private final SendNotificationUseCase sendNotificationUseCase;

    @RabbitListener(queues = "${amqp.queues.notification.name}")
    public void consume(@Payload Transfer transfer) {
        sendNotificationUseCase.execute(transfer);
    }
}
