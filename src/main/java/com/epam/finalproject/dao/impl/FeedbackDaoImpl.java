package com.epam.finalproject.dao.impl;

import com.epam.finalproject.connection.impl.ConnectionPool;
import com.epam.finalproject.dao.FeedbackDao;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.model.builder.FeedbackBuilder;
import com.epam.finalproject.model.entity.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.epam.finalproject.dao.impl.FeedbackStatement.*;

public class FeedbackDaoImpl implements FeedbackDao {
    private static final FeedbackDaoImpl INSTANCE = new FeedbackDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private FeedbackDaoImpl() { }

    public static FeedbackDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(String name, String email, String subject, String message) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementAddFeedback(connection, name, email, subject, message)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Feedback> findAll() throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementSelectAll(connection);
             ResultSet resultSet = statement.executeQuery()) {
            List<Feedback> feedbacks = new ArrayList<>();
            while (resultSet.next()) {
                Feedback feedback = create(resultSet);
                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addReplyMessage(int feedbackId, String message) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = statementUpdateReplyMessage(connection, feedbackId, message)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Feedback create(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(TableColumnName.FEEDBACK_ID);
        String name = resultSet.getString(TableColumnName.FEEDBACK_SENDER_NAME);
        String email = resultSet.getString(TableColumnName.FEEDBACK_SENDER_EMAIL);
        String subject = resultSet.getString(TableColumnName.FEEDBACK_SUBJECT);
        String message = resultSet.getString(TableColumnName.FEEDBACK_MESSAGE);
        Date date = resultSet.getDate(TableColumnName.FEEDBACK_DATETIME);
        String reply = resultSet.getString(TableColumnName.FEEDBACK_REPLY_MESSAGE);
        return FeedbackBuilder.aFeedback()
                .withId(id)
                .withSenderName(name)
                .withSenderEmail(email)
                .withSubject(subject)
                .withMessage(message)
                .withDate(date)
                .withReply(reply)
                .build();
    }
}