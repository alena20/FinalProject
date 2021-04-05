package com.epam.finalproject.model.builder;

import com.epam.finalproject.model.entity.Feedback;

import java.sql.Date;

public class FeedbackBuilder {
    private final Feedback feedback;

    private FeedbackBuilder() {
        feedback = new Feedback();
    }

    public static FeedbackBuilder aFeedback() {
        return new FeedbackBuilder();
    }

    public FeedbackBuilder withId(int id) {
        feedback.setId(id);
        return this;
    }

    public FeedbackBuilder withSenderName(String senderName) {
        feedback.setSenderName(senderName);
        return this;
    }

    public FeedbackBuilder withSenderEmail(String senderEmail) {
        feedback.setSenderEmail(senderEmail);
        return this;
    }

    public FeedbackBuilder withSubject(String subject) {
        feedback.setSubject(subject);
        return this;
    }

    public FeedbackBuilder withMessage(String message) {
        feedback.setMessage(message);
        return this;
    }

    public FeedbackBuilder withDate(Date date) {
        feedback.setDate(date);
        return this;
    }

    public FeedbackBuilder withReply(String reply) {
        feedback.setReply(reply);
        return this;
    }

    public Feedback build() {
        return feedback;
    }
}

