package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.exceptions.ProcessTransferUseCaseException;

public interface ProcessTransferUseCase {
    void execute(Transfer transfer) throws ProcessTransferUseCaseException;
}
