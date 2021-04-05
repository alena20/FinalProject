package com.epam.finalproject.validator;

public class FeedbackValidator extends CommonValidator{

    private static final String SUBJECT_REGEX = "^.{0,30}$";
    private static final String SENDER_NAME_REGEX = "^[\\p{L}\\d]{0,30}$";

    private FeedbackValidator() { }

    public static boolean correctAddParameters(String name, String email, String subject, String message) {
        return notNull(name, email, subject, message) && correctEmail(email) && !message.isBlank()
                && name.matches(SENDER_NAME_REGEX) && subject.matches(SUBJECT_REGEX);
    }

    public static boolean correctReplyParameters(String feedbackId, String email, String subject, String replyMessage) {
        return notNull(feedbackId, email, subject, replyMessage) && correctId(feedbackId)
                && correctEmail(email) && !replyMessage.isBlank();
    }
}
