package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.ValidatePayeeUseCase;
import br.com.joelf.picpay.domain.usecases.MakeTransferUseCase;
import br.com.joelf.picpay.domain.usecases.ValidatePayerBalanceUseCase;
import br.com.joelf.picpay.domain.usecases.exceptions.MakeTransferUseCaseException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MakeTransferUseCaseImpl implements MakeTransferUseCase {

    private final ValidatePayerBalanceUseCase validatePayerBalance;
    private final ValidatePayeeUseCase ValidatePayeeUseCase;
    private final PublishTransferDataProvider publishTransfer;

    @Override
    public void execute(Transfer transfer) {
        validate(transfer);

        try {
            publishTransfer.publish(transfer);
        } catch (RuntimeException ex) {
            throw new MakeTransferUseCaseException("Error on publish transfer");
        }
    }

    private void validate(Transfer transfer) {


    }
}
