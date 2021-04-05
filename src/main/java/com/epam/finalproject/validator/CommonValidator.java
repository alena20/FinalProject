package com.epam.finalproject.validator;

import java.util.Arrays;
import java.util.Objects;

public class CommonValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
            "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final int MAX_EMAIL_LENGTH = 50;

    public static boolean correctId(int id) {
        return id > 0;
    }

    public static boolean correctId(String id) {
        return id != null && id.matches(ID_REGEX);
    }

    protected static boolean correctEmail(String email) {
        return email != null && email.length() <= MAX_EMAIL_LENGTH && email.matches(EMAIL_REGEX);
    }

    protected static boolean notNull(String... parameters) {
        return Arrays.stream(parameters).noneMatch(Objects::isNull);
    }
}
