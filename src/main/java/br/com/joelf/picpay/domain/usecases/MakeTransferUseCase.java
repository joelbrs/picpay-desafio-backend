package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Transfer;

public interface MakeTransferUseCase {
    void execute(Transfer transfer);
}
