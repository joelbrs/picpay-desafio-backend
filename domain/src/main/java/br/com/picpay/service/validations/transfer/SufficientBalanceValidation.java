package br.com.picpay.service.validations.transfer;

import br.com.picpay.domain.Transfer;
import br.com.picpay.exception.ValidationException;
import br.com.picpay.ports.TransferRepository;
import br.com.picpay.validator.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SufficientBalanceValidation implements Validation {

    private final TransferRepository transferRepository;

    @Override
    public RuntimeException isValid(Object object) {
        Transfer transfer = (Transfer) object;
        boolean isSufficientBalance =
            transferRepository.isSufficientBalance(transfer.getPayer(), transfer.getAmount());

        if (!isSufficientBalance) {
            return new ValidationException("Payer's account has unsufficient balance.");
        }
        return null;
    }
}
