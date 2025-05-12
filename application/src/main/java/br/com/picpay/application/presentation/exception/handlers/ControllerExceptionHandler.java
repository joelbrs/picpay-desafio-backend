package br.com.picpay.application.presentation.exception.handlers;

import br.com.picpay.application.presentation.exception.models.BeanValidationException;
import br.com.picpay.application.presentation.exception.models.ResponseException;
import br.com.picpay.exception.BusinessRuleException;
import br.com.picpay.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
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
        ConstraintViolationException ex
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Some fields are not valid.";
        BeanValidationException exception =
            new BeanValidationException(Instant.now(), status.value(), message, request.getRequestURI());

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            exception.add(violation.getPropertyPath().toString(), violation.getMessage());
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
