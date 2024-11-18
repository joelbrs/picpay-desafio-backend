package br.com.joelf.picpay.domain.usecases.exceptions;

public class MakeTransferUseCaseException extends RuntimeException {
    public MakeTransferUseCaseException(String message) {
        super(message);
    }
}
