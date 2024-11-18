package br.com.joelf.picpay.domain.usecases.exceptions;

public class ProcessTransferUseCaseException extends RuntimeException {
    public ProcessTransferUseCaseException(String message) {
        super(message);
    }
}
