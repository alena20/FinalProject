package com.epam.finalproject.validator;

public class TrainerApplicationValidator extends CommonValidator {
    private static final String YEAR_REGEX = "^[12][09]\\d{2}$";
    private static final String INSTITUTION_REGEX = "^[\\p{L}\\s]{2,30}$";

    private TrainerApplicationValidator() { }

    public static boolean correctApplicationParameters(String userId, String institution, String graduationYear) {
        return correctId(userId) && correctInstitution(institution) && correctYear(graduationYear);
    }

    public static boolean correctSendParameters(int id, String institution, String year) {
        return id > 0 && correctInstitution(institution) && correctYear(year);
    }

    private static boolean correctYear(String year) {
        return year != null && year.matches(YEAR_REGEX);
    }

    private static boolean correctInstitution(String institution) {
        return institution != null && institution.matches(INSTITUTION_REGEX);
    }
}
