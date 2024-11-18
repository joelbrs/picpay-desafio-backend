package br.com.joelf.picpay.application.dataprovider;

import br.com.joelf.picpay.domain.entities.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountDataProvider {
    boolean hasBalance(UUID userId, BigDecimal value);
    Account findByUser(UUID userId);
}
