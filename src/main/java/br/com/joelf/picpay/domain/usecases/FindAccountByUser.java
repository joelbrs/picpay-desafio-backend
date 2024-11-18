package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Account;

import java.util.UUID;

public interface FindAccountByUser {
    Account execute(UUID userId);
}
