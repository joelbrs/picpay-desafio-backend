package br.com.picpay.exception;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
