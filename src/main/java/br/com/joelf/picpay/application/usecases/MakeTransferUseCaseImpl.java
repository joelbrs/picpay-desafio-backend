package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.PublishTransfer;
import br.com.joelf.picpay.domain.entities.Account;
import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.FindAccountByUser;
import br.com.joelf.picpay.domain.usecases.MakeTransferUseCase;
import br.com.joelf.picpay.domain.usecases.ValidatePayerBalance;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class MakeTransferUseCaseImpl implements MakeTransferUseCase {

    private final ValidatePayerBalance validatePayerBalance;
    private final FindAccountByUser findAccountByUser;
    private final PublishTransfer publishTransfer;

    @Override
    public void execute(Transfer transfer) {
        validate(transfer);

        publishTransfer.publish(transfer);
    }

    private void validate(Transfer transfer) {
        validateBalance(transfer.getPayer(), transfer.getValue());
        validatePayee(transfer);
    }

    private void validateBalance(UUID payer, BigDecimal value) {
        boolean isPayerBalanceValid = validatePayerBalance.execute(
                payer, value
        );

        if (!isPayerBalanceValid) {
            throw new RuntimeException("Insufficient balance");
        }
    }

    private void validatePayee(Transfer transfer) {
        Account payee = findAccountByUser.execute(transfer.getPayee());

        if (payee == null) {
            throw new RuntimeException("Payee not found");
        }
    }
}
