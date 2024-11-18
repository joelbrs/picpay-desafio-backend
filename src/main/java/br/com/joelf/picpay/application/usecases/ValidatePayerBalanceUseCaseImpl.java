package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.domain.usecases.ValidatePayerBalanceUseCase;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class ValidatePayerBalanceUseCaseImpl implements ValidatePayerBalanceUseCase {

    private final AccountDataProvider accountDataProvider;

    @Override
    public boolean execute(UUID payerId, BigDecimal value) {
        return accountDataProvider.hasBalance(payerId, value);
    }
}
