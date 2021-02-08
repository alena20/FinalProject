package com.epam.finalproject.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface AbstractDao {
    Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    default void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Error during closing result set!", e);
            }
        }
    }

    default void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error during closing statement!", e);
            }
        }
    }

    default void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Unable to rollback!", e);
            }
        }
    }

    default void setAutoCommitTrue(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error("Unable to set autoCommit = true", e);
            }
        }
    }
}
