package br.com.picpay.validator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidationComposite implements Validation {
    private final List<Validation> validations;

    @Override
    public RuntimeException isValid(Object object) {
        for (Validation validation : validations) {
            RuntimeException ex = validation.isValid(object);

            if (ex != null) {
                return ex;
            }
        }

        return null;
    }
}
