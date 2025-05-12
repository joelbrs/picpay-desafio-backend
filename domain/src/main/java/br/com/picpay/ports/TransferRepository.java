package br.com.picpay.ports;

import br.com.picpay.domain.Transfer;

public interface TransferRepository {
    Long create(Transfer transfer);
    void update(Long id, Transfer transfer);
}
