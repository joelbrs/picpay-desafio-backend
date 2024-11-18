package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.ProcessTransferUseCase;
import br.com.joelf.picpay.domain.usecases.SendNotificationUseCase;
import br.com.joelf.picpay.domain.usecases.exceptions.ProcessTransferUseCaseException;
import br.com.joelf.picpay.infraestructure.repositories.clients.authorizer.AuthorizerClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class ProcessTransferUseCaseImpl implements ProcessTransferUseCase {

    private final SendNotificationUseCase sendNotificationUseCase;
    private final AuthorizerClient authorizerClient;
    private final AccountDataProvider accountDataProvider;

    @Transactional
    @Override
    public void execute(Transfer transfer) {
        try {
            authorizerClient.authorize();
        } catch (FeignException e) {
            sendNotificationUseCase.execute(transfer);
            throw new ProcessTransferUseCaseException(e.getMessage());
        }

        updateBalances(transfer);
        sendNotificationUseCase.execute(transfer);
    }

    private void updateBalances(Transfer transfer) {
        accountDataProvider.updateBalance(transfer.getPayee(), transfer.getValue());
        accountDataProvider.updateBalance(transfer.getPayer(), transfer.getValue().negate());
    }
}
