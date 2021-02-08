package com.epam.finalproject.validator;

public class ValidatorPattern {
    public static final String BLANK = "";
    public static final String LOGIN_REGEX = "^[a-zA-Z][a-zA-Z0-9_]{1,19}$";
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9_]{5,30}$";
    public static final String DEPOSIT_AMOUNT_REGEX = "^[1-9]\\d{0,3}$";
    public static final String DAYS_NUMBER_REGEX = "^[1-9]\\d{0,4}$";
    public static final String LOCALE_REGEX = "^((russian)|(english))$";
    public static final String DISCOUNT_REGEX = "^\\d+(\\.\\d{1,2})?$";
    public static final String PHONE_REGEX = "^(((80\\d{2})|(\\+375\\d{2}))[1-9]\\d{6})?$";
    public static final String NAME_REGEX = "^(\\p{L}{0,30})?$";
    public static final int MAX_IMAGE_NAME_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 250;
}
