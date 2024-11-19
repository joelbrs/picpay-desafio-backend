package br.com.joelf.picpay.application.dataprovider;

import br.com.joelf.picpay.domain.entities.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountDataProvider {
    boolean hasBalance(UUID accountId, BigDecimal value);
    Account findById(UUID accountId);
    void updateBalance(UUID userId, BigDecimal value);
    Account create(Account account);
}
