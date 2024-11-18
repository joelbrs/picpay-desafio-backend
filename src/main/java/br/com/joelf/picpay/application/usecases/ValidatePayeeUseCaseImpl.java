package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.domain.entities.Account;
import br.com.joelf.picpay.domain.usecases.ValidatePayeeUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ValidatePayeeUseCaseImpl implements ValidatePayeeUseCase {

    private final AccountDataProvider dataProvider;

    @Override
    public Account execute(UUID userId) {
        Account account = dataProvider.findByUser(userId);

        if (account == null) {
            throw new RuntimeException("Payee not found");
        }
        return account;
    }
}
