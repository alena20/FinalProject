package com.epam.finalproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainerApplicationStatement{
    private static final String SQL_INSERT_APPLICATION =
        "INSERT INTO trainer_applications (user_id, app_institution, app_graduation) " +
                "VALUES (?, ?, ?)";
        private static final String SQL_FIND_BY_ID =
                "SELECT user_id FROM trainer_applications WHERE user_id=?";
        private static final String SQL_SELECT_ALL_APPLICATIONS =
                "SELECT account_id, login, email, role, registration_date, locale, active, first_name, last_name, " +
                        "phone, discount, rating, image_name, money_balance, bought_trainings, institution, " +
                        "graduation, u.user_id, app_institution, app_graduation, short_summary, " +
                        "application_date FROM accounts AS  a JOIN users AS u ON a.account_id=u.user_id " +
                        "JOIN trainer_applications AS t ON a.account_id=t.user_id";
        private static final String SQL_DELETE_APPLICATION =
                "DELETE FROM trainer_applications WHERE user_id=?";

        private TrainerApplicationStatement() { }

        static PreparedStatement statementInsertApplication(Connection connection, int userId, String institution, int graduationYear) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_APPLICATION);
            statement.setInt(1, userId);
            statement.setString(2, institution);
            statement.setInt(3, graduationYear);
            return statement;
        }

        static PreparedStatement statementFindById(Connection connection, int userId) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, userId);
            return statement;
        }

        static PreparedStatement statementSelectAllApplications(Connection connection) throws SQLException {
            return connection.prepareStatement(SQL_SELECT_ALL_APPLICATIONS);
        }

        static PreparedStatement statementDeleteApplication(Connection connection, int userId) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_APPLICATION);
            statement.setInt(1, userId);
            return statement;
        }
}
