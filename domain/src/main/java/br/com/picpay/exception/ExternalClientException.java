package br.com.picpay.exception;

import lombok.Getter;

@Getter
public class ExternalClientException extends RuntimeException {
    private final Integer statusCode;

    public ExternalClientException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
