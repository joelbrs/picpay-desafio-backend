package br.com.picpay.service.validations.transfer;

import br.com.picpay.domain.Transfer;
import br.com.picpay.domain.enums.AccountType;
import br.com.picpay.exception.ValidationException;
import br.com.picpay.ports.AccountRepository;
import br.com.picpay.validator.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuitableAccountTypeForTransfer implements Validation {

    private final AccountRepository accountRepository;

    @Override
    public RuntimeException isValid(Object object) {
        Transfer transfer = (Transfer) object;
        AccountType accountType =
            accountRepository.findAccountType(transfer.getPayer());

        boolean isValidAccountType = accountType.getValue().equals(AccountType.SHOPKEEPER.getValue());

        if (!isValidAccountType) {
            return new ValidationException("Payer's account type is invalid: " + accountType.getValue() + " cannot make a transfer.");
        }
        return null;
    }
}
