package ru.lebedev.servicecarsharingusers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lebedev.servicecarsharingusers.exception.InvalidateDataUserException;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.request.ErrorResponse;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidateDataUserException.class)
    public ResponseEntity<?> carSuitabilityExceptionHandler(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> carNotFoundExceptionHandler(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
