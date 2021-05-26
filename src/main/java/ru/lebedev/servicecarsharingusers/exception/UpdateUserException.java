package ru.lebedev.servicecarsharingusers.exception;

public class UpdateUserException extends Exception{

    public UpdateUserException() {
    }

    public UpdateUserException(String message) {
        super(message);
    }

    public UpdateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateUserException(Throwable cause) {
        super(cause);
    }

    public UpdateUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
