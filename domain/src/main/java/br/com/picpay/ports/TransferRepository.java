package br.com.picpay.ports;

import br.com.picpay.domain.Transfer;

import java.math.BigDecimal;

public interface TransferRepository {
    Long create(Transfer transfer);
    void update(Long id, Transfer transfer);
    boolean isSufficientBalance(Long accountId, BigDecimal amount);
}
