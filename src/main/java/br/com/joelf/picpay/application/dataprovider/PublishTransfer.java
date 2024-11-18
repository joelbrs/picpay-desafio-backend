package br.com.joelf.picpay.application.dataprovider;

import br.com.joelf.picpay.domain.entities.Transfer;

public interface PublishTransfer {
    void publish(Transfer transfer);
}
