package br.com.picpay.application.presentation.exception.handlers;

import br.com.picpay.application.presentation.exception.models.BeanValidationException;
import br.com.picpay.application.presentation.exception.models.ResponseException;
import br.com.picpay.exception.BusinessRuleException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public BeanValidationException validationException(
        HttpServletRequest request,
        MethodArgumentNotValidException ex
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Some fields are not valid.";
        BeanValidationException exception =
            new BeanValidationException(Instant.now(), status.value(), message, request.getRequestURI());

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            exception.add(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return exception;
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseException businessRuleException(
        HttpServletRequest request,
        BusinessRuleException ex
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Business rule failed: " + ex.getMessage();

        return createResponseException(status, request, message);
    }

    private ResponseException createResponseException(HttpStatus status, HttpServletRequest request, String message)  {
        return new ResponseException(Instant.now(), status.value(), message, request.getRequestURI());
    }
}
