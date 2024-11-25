package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.exceptions.MakeTransferUseCaseException;

public interface MakeTransferUseCase {
    void execute(Transfer transfer) throws MakeTransferUseCaseException;
}
