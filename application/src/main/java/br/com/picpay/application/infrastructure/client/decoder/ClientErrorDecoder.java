package br.com.picpay.application.infrastructure.client.decoder;

import br.com.picpay.exception.ExternalClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return new ExternalClientException(response.status(), response.reason());
    }
}
