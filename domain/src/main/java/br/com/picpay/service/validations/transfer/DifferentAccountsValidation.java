package br.com.picpay.service.validations.transfer;

import br.com.picpay.domain.Transfer;
import br.com.picpay.exception.ValidationException;
import br.com.picpay.validator.Validation;

import java.util.Objects;

public class DifferentAccountsValidation implements Validation {

    @Override
    public RuntimeException isValid(Object object) {
        Transfer transfer = (Transfer) object;
        boolean areDifferentAccounts =
            !Objects.equals(transfer.getPayer(), transfer.getPayee());

        if (!areDifferentAccounts) {
            return new ValidationException("Payer can't be the same as payee.");
        }
        return null;
    }
}
