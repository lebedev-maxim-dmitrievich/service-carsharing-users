package ru.lebedev.servicecarsharingusers.exception;

public class UserStatusException extends Exception {

    public UserStatusException() {
    }

    public UserStatusException(String message) {
        super(message);
    }

    public UserStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserStatusException(Throwable cause) {
        super(cause);
    }

    public UserStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
