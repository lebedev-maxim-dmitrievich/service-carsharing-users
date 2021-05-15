package ru.lebedev.servicecarsharingusers.response;

import lombok.Data;

@Data
public class ValidationErrorResponseItem {

    private String field;
    private String message;
}
