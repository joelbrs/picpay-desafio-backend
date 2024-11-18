package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.PublishNotificationDataProvider;
import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.SendNotificationUseCase;
import br.com.joelf.picpay.infraestructure.repositories.clients.notification.NotificationClient;
import feign.FeignException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    private final Integer maxRetries;

    private final PublishNotificationDataProvider publishNotificationDataProvider;
    private final NotificationClient notificationClient;

    @Override
    public void execute(Transfer transfer) {
        try {
            notificationClient.toNotify();
        } catch (FeignException e) {
            retryNotify(0, transfer);
        }
    }

    private void retryNotify(int retry, Transfer transfer) {
        if (retry >= maxRetries) {
            publishNotificationDataProvider.publish(transfer);
            return;
        }

        try {
            notificationClient.toNotify();
        } catch (FeignException e) {
            retryNotify(retry + 1, transfer);
        }
    }
}
