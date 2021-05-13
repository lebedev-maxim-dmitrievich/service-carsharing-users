package ru.lebedev.servicecarsharingusers.constant;

public interface UserRequestConstants {

    String REG_EX_PASSPORT = "\\d{4}\\s\\d{6}";
    String REG_EX_DRIVER_LICENSE = "\\d{4}\\s\\d{6}";
    String REG_EX_FIRST_NAME = "^(\\G[А-Я]{1}[а-яё]+)";
    String REG_EX_LAST_NAME = "^(\\G[А-Я]{1}[а-яё]+)";
    String REG_EX_PATRONYMIC = "^(\\G[А-Я]{1}[а-яё]+)";
}

