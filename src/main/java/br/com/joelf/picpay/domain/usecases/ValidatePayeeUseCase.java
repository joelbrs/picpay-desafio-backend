package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Account;
import br.com.joelf.picpay.domain.usecases.exceptions.ValidatePayeeUseCaseException;

import java.util.UUID;

public interface ValidatePayeeUseCase {
    Account execute(UUID userId) throws ValidatePayeeUseCaseException;
}
