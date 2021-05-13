package ru.lebedev.servicecarsharingusers.exception;

public class InvalidateDataUserException extends Exception {

    public InvalidateDataUserException() {
    }

    public InvalidateDataUserException(String message) {
        super(message);
    }

    public InvalidateDataUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidateDataUserException(Throwable cause) {
        super(cause);
    }

    public InvalidateDataUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}