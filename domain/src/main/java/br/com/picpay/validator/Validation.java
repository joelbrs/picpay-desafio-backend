package br.com.picpay.validator;

public interface Validation {
    RuntimeException isValid(Object object);
}
