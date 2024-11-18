package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.usecases.exceptions.ValidatePayerBalanceUseCaseException;

import java.math.BigDecimal;
import java.util.UUID;

public interface ValidatePayerBalanceUseCase {
    void execute(UUID payerId, BigDecimal value) throws ValidatePayerBalanceUseCaseException;
}
