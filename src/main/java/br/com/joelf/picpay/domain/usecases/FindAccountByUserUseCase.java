package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Account;

import java.util.UUID;

public interface FindAccountByUserUseCase {
    Account execute(UUID userId);
}
