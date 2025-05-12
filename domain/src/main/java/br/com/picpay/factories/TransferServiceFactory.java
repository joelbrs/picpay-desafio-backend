package br.com.picpay.factories;

import br.com.picpay.ports.AccountRepository;
import br.com.picpay.ports.TransferMessaging;
import br.com.picpay.ports.TransferRepository;
import br.com.picpay.service.TransferService;
import br.com.picpay.service.validations.transfer.SufficientBalanceValidation;
import br.com.picpay.service.validations.transfer.SuitableAccountTypeForTransferValidation;
import br.com.picpay.validator.Validation;
import br.com.picpay.validator.ValidationComposite;

import java.util.ArrayList;
import java.util.List;

public class TransferServiceFactory {

    public static TransferService create(
        AccountRepository accountRepository,
        TransferRepository transferRepository,
        TransferMessaging transferMessaging
    ) {
        Validation validation = createTransferValidation(accountRepository, transferRepository);
        return new TransferService(validation, transferRepository, transferMessaging);
    }

    private static Validation createTransferValidation(
        AccountRepository accountRepository,
        TransferRepository transferRepository
    ) {
        List<Validation> createTransferValidations = new ArrayList<>();
        createTransferValidations.add(new SuitableAccountTypeForTransferValidation(accountRepository));
        createTransferValidations.add(new SufficientBalanceValidation(transferRepository));

        return new ValidationComposite(createTransferValidations);
    }
}
