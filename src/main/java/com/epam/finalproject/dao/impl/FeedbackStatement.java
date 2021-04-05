package com.epam.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackStatement {

    private static final String SQL_ADD_FEEDBACK =
            "INSERT INTO feedbacks (sender_name, sender_email, feedback_subject, feedback_message) " +
                    "VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL =
            "SELECT feedback_id, sender_name, sender_email, feedback_subject, feedback_message, feedback_datetime," +
                    " reply_message FROM feedbacks ORDER BY feedback_datetime DESC";
    private static final String SQL_UPDATE_REPLY_MESSAGE =
            "UPDATE feedbacks SET reply_message=? WHERE feedback_id=?";

    private FeedbackStatement() { }

    static PreparedStatement statementAddFeedback(Connection connection, String name, String email, String subject, String message) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_FEEDBACK);
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, subject);
        statement.setString(4, message);
        return statement;
    }

    static PreparedStatement statementSelectAll(Connection connection) throws SQLException {
        return connection.prepareStatement(SQL_SELECT_ALL);
    }

    static PreparedStatement statementUpdateReplyMessage(Connection connection, int feedbackId, String message) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_REPLY_MESSAGE);
        statement.setInt(2, feedbackId);
        statement.setString(1, message);
        return statement;
    }
}
