package br.com.joelf.picpay.domain.usecases.exceptions;

public class ValidatePayerBalanceUseCaseException extends RuntimeException {
    public ValidatePayerBalanceUseCaseException(String message) {
        super(message);
    }
}
