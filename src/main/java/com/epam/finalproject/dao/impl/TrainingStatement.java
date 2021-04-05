package com.epam.finalproject.dao.impl;

import java.sql.*;

public class TrainingStatement {
    private static final String SQL_INSERT_TRAINING =
            "INSERT INTO trainings (trainer_id, client_id, training_date, training_time) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_CLIENT_TRAININGS =
            "SELECT training_id, \n" +
                    "trainer_id, \n" +
                    "(SELECT first_name FROM users WHERE user_id=trainings.trainer_id) AS trainer_first_name, \n" +
                    "(SELECT last_name FROM users WHERE user_id=trainings.trainer_id) AS trainer_last_name, \n" +
                    "client_id, \n" +
                    "(SELECT first_name FROM users WHERE user_id=trainings.client_id) AS client_first_name, \n" +
                    "(SELECT last_name FROM users WHERE user_id=trainings.client_id) AS client_last_name, \n" +
                    "training_date, \n" +
                    "training_time, \n" +
                    "description, \n" +
                    "done, \n" +
                    "training_rating \n" +
                    "FROM trainings WHERE client_id=?";
    private static final String SQL_DECREMENT_TRAININGS =
            "UPDATE users SET bought_trainings=bought_trainings-1 WHERE user_id=?";
    private static final String SQL_INCREMENT_TRAININGS =
            "UPDATE users SET bought_trainings=bought_trainings+1 WHERE user_id=?";
    private static final String SQL_SELECT_TRAINER_TRAININGS =
            "SELECT training_id, \n" +
                    "trainer_id, \n" +
                    "(SELECT first_name FROM users WHERE user_id=trainings.trainer_id) AS trainer_first_name, \n" +
                    "(SELECT last_name FROM users WHERE user_id=trainings.trainer_id) AS trainer_last_name, \n" +
                    "client_id, \n" +
                    "(SELECT first_name FROM users WHERE user_id=trainings.client_id) AS client_first_name, \n" +
                    "(SELECT last_name FROM users WHERE user_id=trainings.client_id) AS client_last_name, \n" +
                    "training_date, \n" +
                    "training_time, \n" +
                    "description, \n" +
                    "done, \n" +
                    "training_rating \n" +
                    "FROM trainings WHERE trainer_id=?";
    private static final String SQL_UPDATE_DESCRIPTION =
            "UPDATE trainings SET description=? WHERE training_id=?";
    private static final String SQL_DELETE_TRAINING =
            "DELETE from trainings WHERE training_id=?";
    private static final String SQL_UPDATE_TRAINING =
            "UPDATE trainings SET training_date=?, training_time=?, description=? WHERE training_id=?";
    private static final String SQL_SET_TRAINING_DONE =
            "UPDATE trainings SET done=true WHERE training_id=?";
    private static final String SQL_UPDATE_TRAINING_RATING =
            "UPDATE trainings SET training_rating=? WHERE training_id=?";
    private static final String SQL_UPDATE_TRAINER_RATING =
            "UPDATE users SET rating=? WHERE user_id=?";
    private static final String SQL_AVERAGE_RATING =
            "SELECT AVG(training_rating) AS trainer_rating " +
                    "FROM (SELECT training_rating FROM trainings WHERE trainer_id=?) AS temp " +
                    "WHERE training_rating!=0";
    private static final String SQL_SELECT_TRAINING_BY_ID =
            "SELECT training_id, \n" +
                    "trainer_id, \n" +
                    "(SELECT first_name FROM users WHERE user_id=trainings.trainer_id) AS trainer_first_name, \n" +
                    "(SELECT last_name FROM users WHERE user_id=trainings.trainer_id) AS trainer_last_name, \n" +
                    "client_id, \n" +
                    "(SELECT first_name FROM users WHERE user_id=trainings.client_id) AS client_first_name, \n" +
                    "(SELECT last_name FROM users WHERE user_id=trainings.client_id) AS client_last_name, \n" +
                    "training_date, \n" +
                    "training_time, \n" +
                    "description, \n" +
                    "done, \n" +
                    "training_rating \n" +
                    "FROM trainings WHERE training_id=?";
    private static final String SQL_SELECT_TRAINER_CLIENTS =
            "SELECT account_id, first_name, last_name, email, phone, image_name\n" +
                    "FROM accounts JOIN users ON account_id=user_id JOIN trainings ON client_id=user_id\n" +
                    "WHERE trainer_id=?\n" +
                    "GROUP BY client_id";

    private TrainingStatement() { }

    static PreparedStatement statementInsertTraining(Connection connection, int trainerId, int clientId, Date trainingDate, Time trainingTime) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TRAINING, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, trainerId);
        statement.setInt(2, clientId);
        statement.setDate(3, trainingDate);
        statement.setTime(4, trainingTime);
        return statement;
    }

    static PreparedStatement statementSelectClientTrainings(Connection connection, int clientId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CLIENT_TRAININGS);
        statement.setInt(1, clientId);
        return statement;
    }

    static PreparedStatement statementDecrementTrainings(Connection connection, int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DECREMENT_TRAININGS);
        statement.setInt(1, userId);
        return statement;
    }

    static PreparedStatement statementIncrementTrainings(Connection connection, int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INCREMENT_TRAININGS);
        statement.setInt(1, userId);
        return statement;
    }

    static PreparedStatement statementSelectTrainerTrainings(Connection connection, int trainerId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRAINER_TRAININGS);
        statement.setInt(1, trainerId);
        return statement;
    }

    static PreparedStatement statementUpdateDescription(Connection connection, int trainingId, String description) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DESCRIPTION);
        statement.setString(1, description);
        statement.setInt(2, trainingId);
        return statement;
    }

    static PreparedStatement statementDeleteTraining(Connection connection, int trainingId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TRAINING);
        statement.setInt(1, trainingId);
        return statement;
    }

    static PreparedStatement statementUpdateTraining(Connection connection, Date date, Time time, String descripition, int id)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TRAINING);
        statement.setDate(1, date);
        statement.setTime(2, time);
        statement.setString(3, descripition);
        statement.setInt(4, id);
        return statement;
    }

    static PreparedStatement statementSetTrainingDone(Connection connection, int trainingId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SET_TRAINING_DONE);
        statement.setInt(1, trainingId);
        return statement;
    }

    static PreparedStatement statementUpdateTrainingRating(Connection connection, int trainingId, int rating) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TRAINING_RATING);
        statement.setInt(1, rating);
        statement.setInt(2, trainingId);
        return statement;
    }

    static PreparedStatement statementUpdateTrainerRating(Connection connection, int id, double rating) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TRAINER_RATING);
        statement.setDouble(1, rating);
        statement.setInt(2, id);
        return statement;
    }

    static PreparedStatement statementAverageRating(Connection connection, int trainerId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_AVERAGE_RATING);
        statement.setInt(1, trainerId);
        return statement;
    }

    static PreparedStatement statementSelectTrainingById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRAINING_BY_ID);
        statement.setInt(1, id);
        return statement;
    }

    static PreparedStatement statementSelectTrainerClients(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRAINER_CLIENTS);
        statement.setInt(1, id);
        return statement;
    }
}
