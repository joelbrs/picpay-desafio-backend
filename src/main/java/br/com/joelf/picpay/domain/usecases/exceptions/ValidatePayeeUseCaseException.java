package br.com.joelf.picpay.domain.usecases.exceptions;

public class ValidatePayeeUseCaseException extends RuntimeException {
    public ValidatePayeeUseCaseException(String message) {
        super(message);
    }
}
