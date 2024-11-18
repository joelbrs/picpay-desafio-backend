package br.com.joelf.picpay.domain.usecases.exceptions;

public class SignUpUseCaseException extends RuntimeException {
    public SignUpUseCaseException(String message) {
        super(message);
    }
}
