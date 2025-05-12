package br.com.picpay.application.presentation.exception.models;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BeanValidationException extends ResponseException {
    private final List<FieldMessage> messages = new ArrayList<>();

    public BeanValidationException(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public void add(String field, String message) {
        messages.add(new FieldMessage(field, message));
    }
}
