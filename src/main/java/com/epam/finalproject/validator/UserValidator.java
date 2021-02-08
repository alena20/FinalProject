package com.epam.finalproject.validator;

import com.epam.finalproject.util.RequestParameterName;

import java.util.Map;

public class UserValidator extends AbstractValidator{

    private static final ValidationErrorSet errorSet = ValidationErrorSet.getInstance();

    private UserValidator() {
    }

    public static boolean correctUpdateSummaryParameters(int id, String description) {
        return correctId(id) && correctDescription(description);
    }

    public static boolean correctUpdateImageParameters(int id, String imageName) {
        return correctId(id) && correctImageName(imageName);
    }

    public static boolean correctPersonalDataParameters(int userId, String firstName, String lastName, String phone) {
        return correctId(userId) && correctName(firstName) && correctName(lastName) && correctPhone(phone);
    }

    public static boolean correctAccountDataParameters(String email, String locale, String newPassword, String repeatPassword) {
        return correctEmail(email) && correctLocale(locale) && correctPasswords(newPassword, repeatPassword);
    }

    private static boolean correctPasswords(String password, String repeatPassword) {
        boolean option1 = correctPassword(password) && correctPassword(repeatPassword) && password.equals(repeatPassword);
        boolean option2 = notNull(password, repeatPassword) && password.isEmpty() && repeatPassword.isEmpty();
        return option1 || option2;
    }

    public static boolean correctUpdateDiscountParameters(String id, String discount) { return correctId(id) && correctDiscount(discount);}

    public static boolean correctLoginParameters(Map<String, String> parameters) {
        boolean result = true;
        String login = parameters.get(RequestParameterName.LOGIN);
        if (!correctLogin(login)) {
            parameters.put(RequestParameterName.LOGIN, ValidatorPattern.BLANK);
            errorSet.add(ValidationError.INVALID_LOGIN_FORMAT);
            result = false;
        }
        String password = parameters.get(RequestParameterName.LOGIN_PASSWORD);
        if (!correctPassword(password)) {
            parameters.put(RequestParameterName.LOGIN_PASSWORD, ValidatorPattern.BLANK);
            errorSet.add(ValidationError.INVALID_PASSWORD_FORMAT);
            result = false;
        }
        return result;
    }

    public static boolean correctRegisterParameters(Map<String, String> parameters) {
        boolean result = true;
        String login = parameters.get(RequestParameterName.REGISTRATION_LOGIN);
        if (!correctLogin(login)) {
            parameters.put(RequestParameterName.REGISTRATION_LOGIN, ValidatorPattern.BLANK);
            errorSet.add(ValidationError.INVALID_LOGIN_FORMAT);
            result = false;
        }
        String password = parameters.get(RequestParameterName.REGISTRATION_PASSWORD);
        if (!correctPassword(password)) {
            parameters.put(RequestParameterName.REGISTRATION_PASSWORD, ValidatorPattern.BLANK);
            errorSet.add(ValidationError.INVALID_PASSWORD_FORMAT);
            result = false;
        }
        String email = parameters.get(RequestParameterName.REGISTRATION_EMAIL);
        if (!correctEmail(email)) {
            parameters.put(RequestParameterName.REGISTRATION_EMAIL, ValidatorPattern.BLANK);
            errorSet.add(ValidationError.INVALID_EMAIL_FORMAT);
            result = false;
        }
        String passwordRepeat = parameters.get(RequestParameterName.REPEAT_PASSWORD);
        if (!correctPassword(passwordRepeat) || !passwordRepeat.equals(password)) {
            parameters.put(RequestParameterName.REGISTRATION_PASSWORD, ValidatorPattern.BLANK);
            parameters.put(RequestParameterName.REPEAT_PASSWORD, ValidatorPattern.BLANK);
            errorSet.add(ValidationError.PASSWORDS_ARE_NOT_EQUAL);
            result = false;
        }
        return result;
    }

    public static boolean correctDepositAmount(String amount) {
        return amount != null && amount.matches(ValidatorPattern.DEPOSIT_AMOUNT_REGEX);
    }

    private static boolean correctLogin(String login) {
        return login != null && login.matches(ValidatorPattern.LOGIN_REGEX);
    }

    private static boolean correctPassword(String password) {
        return password != null && password.matches(ValidatorPattern.PASSWORD_REGEX);
    }

    private static boolean correctLocale(String locale) {
        return locale == null || locale.matches(ValidatorPattern.LOCALE_REGEX);
    }

    private static boolean correctDiscount(String discount) {
        return discount != null && discount.matches(ValidatorPattern.DISCOUNT_REGEX);
    }

    private static boolean correctName(String name) {
        return name != null && name.matches(ValidatorPattern.NAME_REGEX);
    }

    private static boolean correctPhone(String phone) {
        return phone != null && phone.matches(ValidatorPattern.PHONE_REGEX);
    }

    private static boolean correctImageName(String imageName) {
        return imageName != null && imageName.length() <= ValidatorPattern.MAX_IMAGE_NAME_LENGTH;
    }

    private static boolean correctDescription(String description) {
        return description != null && description.length() <= ValidatorPattern.MAX_DESCRIPTION_LENGTH;
    }

    public static boolean correctDaysNumber(String days) {
        return days == null || days.matches(ValidatorPattern.DAYS_NUMBER_REGEX);
    }
}
