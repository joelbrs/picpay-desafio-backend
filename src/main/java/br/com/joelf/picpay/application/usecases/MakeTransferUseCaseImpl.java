package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.application.dataprovider.exceptions.PublishTransferDataProviderException;
import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.ValidatePayeeUseCase;
import br.com.joelf.picpay.domain.usecases.MakeTransferUseCase;
import br.com.joelf.picpay.domain.usecases.ValidatePayerBalanceUseCase;
import br.com.joelf.picpay.domain.usecases.exceptions.MakeTransferUseCaseException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class MakeTransferUseCaseImpl implements MakeTransferUseCase {

    private final ValidatePayerBalanceUseCase validatePayerBalance;
    private final ValidatePayeeUseCase ValidatePayeeUseCase;
    private final PublishTransferDataProvider publishTransferDataProvider;

    @Transactional(readOnly = true)
    @Override
    public void execute(Transfer transfer) {
        validate(transfer);

        try {
            publishTransferDataProvider.publish(transfer);
        } catch (PublishTransferDataProviderException e) {
            throw new MakeTransferUseCaseException(e.getMessage());
        }
    }

    private void validate(Transfer transfer) {
        validatePayerBalance.execute(transfer.getPayer(), transfer.getValue());
        ValidatePayeeUseCase.execute(transfer.getPayee());
    }
}
