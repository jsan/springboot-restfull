package com.estudo.springboot.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(@NotNull ResourceNotFoundException exception,
                                                                        @NotNull WebRequest webRequest)
    {
        ErrorDetails errorDetails = errorDetails(exception, webRequest, "USER_NOT_FOUND!");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> emailAlreadyExistsException(@NotNull EmailAlreadyExistsException exception,
                                                                        @NotNull WebRequest webRequest)
    {
        ErrorDetails errorDetails = errorDetails(exception, webRequest, "EMAIL_ALREADY_EXISTS!");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(@NotNull Exception exception,
                                                              @NotNull WebRequest webRequest)
    {
        ErrorDetails errorDetails = errorDetails(exception, webRequest, "INTERNAL SERVER ERROR!");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDetails errorDetails(Exception exception, WebRequest webRequest, String message)
    {
        return new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                message
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  @NotNull WebRequest webRequest)
    {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach(error -> {
                    errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                }
        );

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                errors.entrySet()
                        .stream()
                        .map(error -> error.getKey() + ":" + error.getValue())
                        .collect(Collectors.joining(", ")),
                webRequest.getDescription(false),
                "BAD REQUEST");

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
