package br.com.picpay.ports;

import br.com.picpay.exception.ExternalClientException;

public interface AuthorizerClient {
    void authorize() throws ExternalClientException;
}
