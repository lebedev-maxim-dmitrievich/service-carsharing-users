package ru.lebedev.servicecarsharingusers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lebedev.servicecarsharingusers.exception.InvalidateDataUserException;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.exception.UserStatusException;
import ru.lebedev.servicecarsharingusers.request.ErrorResponse;
import ru.lebedev.servicecarsharingusers.response.ValidationErrorResponse;
import ru.lebedev.servicecarsharingusers.response.ValidationErrorResponseItem;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidateDataUserException.class)
    public ResponseEntity<?> carSuitabilityExceptionHandler(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserStatusException.class)
    public ResponseEntity<?> userStatusExceptionHandler(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        e.getFieldErrors().forEach(fieldError -> {
            ValidationErrorResponseItem item = new ValidationErrorResponseItem();
            item.setField(fieldError.getField());
            item.setMessage(fieldError.getDefaultMessage());
            validationErrorResponse.getValidationErrors().add(item);
        });

        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
