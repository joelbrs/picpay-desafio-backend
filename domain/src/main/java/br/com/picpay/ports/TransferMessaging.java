package br.com.picpay.ports;

import br.com.picpay.domain.Transfer;

public interface TransferMessaging {
    void send(Transfer transfer);
}
