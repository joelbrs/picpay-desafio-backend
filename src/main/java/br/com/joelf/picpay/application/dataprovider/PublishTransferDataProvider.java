package br.com.joelf.picpay.application.dataprovider;

import br.com.joelf.picpay.application.dataprovider.exceptions.PublishTransferDataProviderException;
import br.com.joelf.picpay.domain.entities.Transfer;

public interface PublishTransferDataProvider {
    void publish(Transfer transfer) throws PublishTransferDataProviderException;
}
