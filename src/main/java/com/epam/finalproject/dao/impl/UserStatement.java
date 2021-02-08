package com.epam.finalproject.dao.impl;

import com.epam.finalproject.model.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserStatement {
    private static final String SQL_INSERT_ACCOUNT =
            "INSERT INTO accounts(login, password, email, role) VALUES(?,?,?,?)";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users(user_id) VALUES(?)";

    private UserStatement() {}
    static PreparedStatement statementInsertAccount(Connection connection, String login, String password,
                                                    String email) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setString(4, UserRole.CLIENT.toString());
        return statement;
    }

    static PreparedStatement statementInsertUser(Connection connection, int accountId) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_INSERT_USER);
        statement.setInt(1, accountId);
        return statement;
    }

}
