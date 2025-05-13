package br.com.picpay.service;

import br.com.picpay.domain.Transfer;
import br.com.picpay.domain.enums.TransferStatus;
import br.com.picpay.exception.BusinessRuleException;
import br.com.picpay.exception.ExternalClientException;
import br.com.picpay.ports.AuthorizerClient;
import br.com.picpay.ports.TransferMessaging;
import br.com.picpay.ports.TransferRepository;
import br.com.picpay.validator.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransferService {

    private final Validation createTransferValidation;

    private final TransferRepository transferRepository;
    private final TransferMessaging transferMessaging;

    private final AuthorizerClient authorizerClient;

    public Transfer create(Transfer transfer) {
        RuntimeException ex = createTransferValidation.isValid(transfer);

        if (ex != null) {
            throw new BusinessRuleException(ex.getMessage(), ex.getCause());
        }

        transfer.setStatus(TransferStatus.PROCESSING);

        Long transferId = transferRepository.create(transfer);
        transfer.setId(transferId);

        Thread.startVirtualThread(() -> transferMessaging.send(transfer));
        return transfer;
    }

    public void proccess(Transfer transfer) {
        transfer.setStatus(TransferStatus.COMPLETED);
        try {
            authorizerClient.authorize();
        } catch (ExternalClientException ex) {
            transfer.setStatus(TransferStatus.ERROR);
        }

        transferRepository.update(transfer.getId(), transfer);
        //notify customer
    }
}
