package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.entities.Transfer;

public interface SendNotificationUseCase {
    void execute(Transfer transfer);
}
